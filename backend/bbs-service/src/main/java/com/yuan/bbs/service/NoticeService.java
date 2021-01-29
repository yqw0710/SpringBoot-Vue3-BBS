package com.yuan.bbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.entity.Comment;
import com.yuan.bbs.entity.Notice;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 已读一条或全部通知(如果id为0)
     *
     * @param id  通知记录的id
     * @param uid uid
     */
    void read(Integer id, Integer uid);

    /**
     * 点赞通知
     *
     * @param notifier 通知者id
     * @param itemId   资源id
     */
    void likeNotice(Integer notifier, Integer itemId, Integer itemType);

    /**
     * 评论通知
     */
    void commentNotice(Integer notifier, Comment comment);

    /**
     * 删除未读的点赞通知(收回)
     */
    void cancelLikeNotice(Integer notifier, Integer itemId, Integer itemType);

}
