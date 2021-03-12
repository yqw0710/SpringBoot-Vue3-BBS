package com.yuan.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.common.interfaces.Likeable;
import com.yuan.bbs.entity.Comment;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
public interface CommentService extends IService<Comment>, Likeable {

    /**
     * 分页获取某个资源下的评论
     *
     * @param itemType 资源类型
     * @param itemId   资源主键
     */
    IPage<Comment> listComments(Integer itemType, Integer itemId, Integer num, Integer size);

    /**
     * 分页获取某个评论下的回复
     */
    IPage<Comment> listReplyOfComment(Integer pid, Integer num, Integer size);


    /**
     * 保存评论
     */
    Comment saveComment(Integer uid, Comment comment);

    /**
     * 删除评论
     */
    boolean deleteComment(Integer id, Integer uid);


}
