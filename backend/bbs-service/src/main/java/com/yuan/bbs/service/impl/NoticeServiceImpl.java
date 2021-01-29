package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.enums.ItemType;
import com.yuan.bbs.common.enums.NoticeType;
import com.yuan.bbs.entity.Comment;
import com.yuan.bbs.entity.Notice;
import com.yuan.bbs.entity.Post;
import com.yuan.bbs.entity.Translation;
import com.yuan.bbs.mapper.NoticeMapper;
import com.yuan.bbs.mapper.UserMapper;
import com.yuan.bbs.service.CommentService;
import com.yuan.bbs.service.NoticeService;
import com.yuan.bbs.service.PostService;
import com.yuan.bbs.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    TranslationService ts;
    @Autowired
    CommentService cs;
    @Autowired
    PostService ps;
    @Resource
    UserMapper userMapper;

    @Override
    public void read(Integer id, Integer uid) {
        UpdateWrapper<Notice> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("receiver", uid).set("status", 1);
        if (id != null && id != 0) updateWrapper.eq("id", id);
        this.update(updateWrapper);
    }

    /**
     * @param notifier 通知者id
     * @param itemId   资源id
     * @param itemType
     */
    @Override
    public void likeNotice(Integer notifier, Integer itemId, Integer itemType) {
        Notice n = new Notice();
        int receiver;
        String Nickname = userMapper.getNicknameById(notifier);
        StringBuilder title = new StringBuilder(Nickname + NoticeType.LIKE + "了你的");
        // 选择接收者并生成标题 xxx点赞了你的xxx:xxxxxx
        if (itemType == ItemType.TRANSLATION.getType()) {
            Translation item = ts.getById(itemId);
            receiver = item.getUid();
            title.append(ItemType.TRANSLATION.getDesc() + ":" + item.getTitle());
        } else if (itemType == ItemType.COMMENT.getType()) {
            Comment item = cs.getById(itemId);
            receiver = item.getFromId();
            title.append(ItemType.COMMENT.getDesc() + ":" + item.getContent());
        } else if (itemType == ItemType.POST.getType()) {
            Post item = ps.getById(itemId);
            receiver = item.getUid();
            title.append(ItemType.POST.getDesc() + ":" + item.getTitle());
        } else {
            receiver = -1; // else 要结尾.随便搞个-1
        }
        // 自己就不给自己发消息 或者前端实现 你给你自己点了个赞
        if (receiver == notifier) return;
        n.setType(NoticeType.LIKE.getType())
                .setItemId(itemId)
                .setItemType(itemType)
                .setNotifier(notifier)
                .setReceiver(receiver)
                .setTitle(title.toString());
        this.save(n);
    }

    @Override
    public void cancelLikeNotice(Integer notifier, Integer itemId, Integer itemType) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notifier", notifier)
                .eq("item_id", itemId)
                .eq("item_type", itemType)
                .eq("type", 2)
                .eq("status", 0);
        this.remove(queryWrapper);
    }

    @Override
    public void commentNotice(Integer notifier, Comment comment) {
        int receiver; // 接收者要具体判断
        String nickname = userMapper.getNicknameById(notifier);
        Notice n = new Notice();
        // pid==0 是对资源的评论
        // 标题和内容的设置
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            // 待用枚举类优化
            int type = comment.getItemType();
            String tmp = null;
            if ((type == ItemType.TRANSLATION.getType())) {
                receiver = ts.getById(comment.getItemId()).getUid();
                tmp = ItemType.TRANSLATION.getDesc();
            } else {
                receiver = ps.getById(comment.getItemId()).getUid();
                tmp = ItemType.ARTICLE.getDesc();
            }
            n.setTitle(nickname + "评论了你的" + tmp + ":点击跳转")
                    .setContent(comment.getContent());
        } else {
            // pid!=0 说明是个子评论 接收者是toId
            receiver = comment.getToId();
            n.setTitle(nickname + "回复/评论了你的评论")
                    .setContent(comment.getContent());
        }
        n.setNotifier(notifier)
                .setReceiver(receiver)
                // 评论要指定资源,通知也要
                .setItemType(comment.getItemType())
                .setItemId(comment.getItemId())
                .setType(4);// 评论通知
        this.save(n);
    }


}
