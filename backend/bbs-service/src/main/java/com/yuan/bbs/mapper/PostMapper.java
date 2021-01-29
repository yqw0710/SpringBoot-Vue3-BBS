package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bbs.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 帖子 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-28
 */
public interface PostMapper extends BaseMapper<Post> {
    @ResultType(Post.class)
    @Select("select p.id,u.id as uid,u.nickname,u.avatar,p.title," +
            "p.category,p.hot,p.pv,p.likes,p.comments,p.collections," +
            "p.last_reply,p.gmt_create,p.gmt_modified,p.attachment,p.content from y_user u," +
            "y_post p where u.id=p.uid and p.status=1 order by p.id desc")
    Page<Post> getPostAndPage(Page<Post> page);

    @ResultType(Post.class)
    @Select("select p.id,u.id as uid,u.nickname,u.avatar,p.title," +
            "p.category,p.hot,p.pv,p.likes,p.comments,p.collections," +
            "p.last_reply,p.gmt_create,p.gmt_modified ,p.attachment,p.content from y_user u," +
            "y_post p where u.id=p.uid and p.category =  #{category} and p.status=1 order by p.id desc")
    Page<Post> getPostAndPageWithCategory(Page<Post> page, String category);

    @Update("update y_post set likes = likes + #{likes} where id = #{id}")
    void updateLike(@Param("id") int id, @Param("likes") int likes);
}
