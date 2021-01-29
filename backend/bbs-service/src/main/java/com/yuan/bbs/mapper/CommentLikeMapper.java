package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bbs.entity.CommentLike;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 文章点赞表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-21
 */
public interface CommentLikeMapper extends BaseMapper<CommentLike> {
    @Insert("insert into y_comment_like(`cid`, `uid`, `status`) VALUES (#{cid}, #{uid}, #{status }) on duplicate key update status=#{status}")
    void insertOrUpdateStatus(@Param("cid") Integer tid, @Param("uid") Integer uid, @Param("status") Integer status);


    @ResultType(Integer.class)
    @Select("select cid from y_comment_like where uid = #{uid} and status= 1 ")
    Integer[] getUserLikes(Integer uid);
}
