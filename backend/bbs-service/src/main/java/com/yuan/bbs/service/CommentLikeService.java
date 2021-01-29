package com.yuan.bbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.common.interfaces.Like;
import com.yuan.bbs.entity.CommentLike;

/**
 * <p>
 * 文章点赞表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-21
 */
public interface CommentLikeService extends IService<CommentLike>, Like {

    void saveOrUpdateStatus(Integer id, Integer uid, Integer status);

    Integer[] getLikes(Integer uid);
}
