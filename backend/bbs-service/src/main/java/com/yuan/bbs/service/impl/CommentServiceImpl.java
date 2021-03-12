package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.enums.ItemType;
import com.yuan.bbs.common.utils.SensitiveFilterUtil;
import com.yuan.bbs.entity.Comment;
import com.yuan.bbs.mapper.CommentMapper;
import com.yuan.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author yuan
 * @since 2021-03-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    SensitiveFilterUtil sensitiveFilterUtil;
    @Resource
    CommentMapper mapper;

    @Override
    public IPage<Comment> listComments(Integer itemType, Integer itemId, Integer num, Integer size) {

        Page<Comment> commentPaged = mapper.listComment(itemType, itemId, new Page<>(num, size));

        List<Integer> collect = commentPaged.getRecords()
                .stream().map(Comment::getId).collect(Collectors.toList());
        if (collect.isEmpty()) return commentPaged;
        System.out.println(collect);
        List<Comment> reply = mapper.treeReplyOfComment(collect);

        // 遍历子评论，将其挂载到其夫评论上 每个评论最多取3条
        reply.forEach(child -> commentPaged.getRecords().forEach(parent -> {

            if (parent.getChildren() == null)
                parent.setChildren(new ArrayList<>(3));

            if (child.getParentId().equals(parent.getId()))
                parent.getChildren().add(child);

        }));

        return commentPaged;
    }

    @Override
    public IPage<Comment> listReplyOfComment(Integer pid, Integer num, Integer size) {
        return mapper.listReply(pid, new Page<>(num, size));
    }


    // TODO 发送通知
    @Override
    public Comment saveComment(Integer uid, Comment comment) {

        comment.setId(null).setLikes(null).setStatus(null).setGmtCreate(null).setFromId(uid);

        comment.setContent(sensitiveFilterUtil.filter(comment.getContent()));// 过滤铭感词

        this.save(comment);

        // 根据item_id item_type 确定一条记录 然后更新帖子的评论数
        if (comment.getItemType() == ItemType.POST.getType()) {
            mapper.updatePostComments(comment.getItemId(), 1);
        }

        return comment;
    }

    @Override
    public boolean deleteComment(Integer id, Integer uid) {
        return this.update(new UpdateWrapper<Comment>().eq("from_id", uid).eq("id", id).set("status", 3));
    }


    @Override
    public void updateLikes(int id, int likes) {
        this.update(new UpdateWrapper<Comment>().eq("id", id).setSql("likes=likes+" + likes));
    }
}
