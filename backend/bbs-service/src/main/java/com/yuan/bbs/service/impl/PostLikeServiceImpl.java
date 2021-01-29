package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.entity.PostLike;
import com.yuan.bbs.mapper.PostLikeMapper;
import com.yuan.bbs.service.PostLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 帖子点赞表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2020-07-28
 */
@Service

public class PostLikeServiceImpl extends ServiceImpl<PostLikeMapper, PostLike> implements PostLikeService {

    @Resource
    PostLikeMapper mapper;

    @Override
    public void saveOrUpdateStatus(Integer id, Integer uid, Integer status) {
        mapper.insertOrUpdateStatus(id, uid, status);
    }

    @Override
    public Integer[] getLikes(Integer uid) {
        return mapper.getUserLikes(uid);
    }
}
