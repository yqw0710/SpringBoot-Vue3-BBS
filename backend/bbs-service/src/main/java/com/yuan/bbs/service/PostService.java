package com.yuan.bbs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.common.interfaces.Likeable;
import com.yuan.bbs.common.pojo.PostDto;
import com.yuan.bbs.entity.Post;


/**
 * <p>
 * 帖子 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-28
 */
public interface PostService extends IService<Post>, Likeable {

    Page<Post> getPostList(int num, int size, String category);

    Integer edit(PostDto postDto, Integer uid);

    void addPv(Integer pid);

    Post getPostByIDAndAddPv(Integer pid);
}
