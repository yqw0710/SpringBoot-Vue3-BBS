package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.enums.ItemType;
import com.yuan.bbs.common.utils.SensitiveFilterUtil;
import com.yuan.bbs.entity.Comment;
import com.yuan.bbs.mapper.CommentMapper;
import com.yuan.bbs.service.CommentService;
import com.yuan.bbs.service.LikeService;
import com.yuan.bbs.service.NoticeService;
import com.yuan.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类,先不加缓存
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    SensitiveFilterUtil sensitiveFilterUtil;
    @Autowired
    UserService userservice;
    @Autowired
    LikeService likeService;
    @Autowired
    NoticeService noticeService;
    @Resource
    CommentMapper mapper;

    /**
     * @param type 资源类型
     * @param id   资源id
     */
    @Override
    public IPage<Comment> selectPageParent(Integer type, Integer id, Integer num, Integer size) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>()
                .eq("item_type", type)
                .eq("item_id", id)
                .eq("parent_id", 0)
                .eq("status", 1);
        IPage<Comment> parent = this.page(new Page<>(num, size), queryWrapper);
        // 每条父评论携带3条子评论
        parent.getRecords().forEach(c -> {
            // 获取子评论 这可以根据点赞数排行获取点赞最多的评论,而不是按顺序.其次要加缓存
            IPage<Comment> children = this.selectPageChildren(c.getId(), 1, 3);
            List<Comment> childArr = children.getRecords();
            // 挂载子评论并获取头像和赞数 ps:觉得可用多表查询,结果不用在这么麻烦的处理- -! 数据库没学好
            c.setChildren(childArr)
                    .setChildrenCount(children.getTotal())
                    .setFromAvatar(userservice.getAvatarByUid(c.getFromId()))
                    .setLikes(c.getLikes() + likeService.getLikeCountByType(ItemType.COMMENT.getType(), c.getId()));
        });
        return parent;
    }

    @Override
    public IPage<Comment> selectPageChildren(Integer pid, Integer num, Integer size) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>()
                .eq("parent_id", pid)
                .eq("status", 1);
        Page<Comment> page = this.page(new Page<>(num, size), queryWrapper);
        // 设置子评论的头像和点赞数
        page.getRecords().forEach(
                c -> c.setFromAvatar(userservice.getAvatarByUid(c.getFromId()))
                        .setLikes(c.getLikes() + likeService.getLikeCountByType(ItemType.COMMENT.getType(), c.getId())));
        return page;
    }

    @Override
    public Comment getLastComment(Integer type, Integer id, Integer uid) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
        queryWrapper.orderByDesc("id")
                .eq("item_type", type).eq("item_id", id)
                .eq("from_id", uid).last("limit 1");
        return getOne(queryWrapper, false);
    }

    @Override
    public Comment getLastComment(Comment comment) {
        return getLastComment(comment.getItemType(), comment.getItemId(), comment.getFromId());
    }

    @Override
    public Comment saveComment(Integer uid, Comment comment) {
        comment.setId(null).setLikes(null).setStatus(null).setGmtCreate(null).setFromId(uid);
        comment.setContent(sensitiveFilterUtil.filter(comment.getContent()));// 过滤铭感词
        this.save(comment);
        // 根据item_id item_type 确定一条记录
        if (comment.getItemType() == ItemType.POST.getType()) {
            System.out.println("add 1");
            System.out.println();
            // 更新帖子收到的评论数
            mapper.updatePostComments(comment.getItemId(), 1);
        }
        Comment last = this.getLastComment(comment);
        noticeService.commentNotice(uid, last);
        return last;
    }

    @Override
    public void deleteComment(int id, int uid) {
        // 评论数收获的评论数不减
        this.update(new UpdateWrapper<Comment>().eq("from_id", uid).eq("id", id).set("status", 3));
    }

    @Override
    public void updateLikes(int id, int likes) {
        this.update(new UpdateWrapper<Comment>()
                .eq("id", id).setSql("likes=likes+" + likes)
        );
    }
}
