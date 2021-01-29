package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bbs.entity.PostLike;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 帖子点赞表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-28
 */
public interface PostLikeMapper extends BaseMapper<PostLike> {

    @ResultType(Integer.class)
    @Select("select pid from y_post_like where uid = #{uid} and status= 1 ")
    Integer[] getUserLikes(Integer uid);

    @Insert("insert into y_post_like(`pid`, `uid`, `status`) VALUES (#{pid}, #{uid}, #{status }) on duplicate key update status=#{status}")
    void insertOrUpdateStatus(@Param("pid") Integer pid, @Param("uid") Integer uid, @Param("status") Integer status);
}
