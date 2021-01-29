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
     * 分页获取父级评论,还要检查参数大小 这样耗时还挺高的..
     */
    IPage<Comment> selectPageParent(Integer type, Integer id, Integer num, Integer size);

    /**
     * 分页获取子评论,只需要父评论的id
     */
    IPage<Comment> selectPageChildren(Integer pid, Integer num, Integer size);


    /**
     * 获取当前用户最新发布的评论 用于返回
     */
    Comment getLastComment(Integer type, Integer id, Integer uid);

    /**
     * 获取当前用户最新发布的评论 用于插入评论后返回
     */
    Comment getLastComment(Comment comment);

    /**
     * 保存评论
     */
    Comment saveComment(Integer uid, Comment comment);

    void deleteComment(int id, int uid);
}
