package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bbs.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Update("update y_post set comments=comments+ #{val}  where id = #{id} ")
    void updatePostComments(@Param("id") int id, @Param("val") int val);
}
