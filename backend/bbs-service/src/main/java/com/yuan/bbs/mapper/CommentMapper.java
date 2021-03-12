package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bbs.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    // 获取评论集合中每个评论的前三条回复(子评论)
    @ResultType(Comment.class)
    @Select("<script>" +
            "SELECT a.id ,a.parent_id,a.from_id,a.from_name,a.to_id,a.to_name,a.likes,a.content,a.gmt_create,u.avatar " +
            "FROM y_comment a JOIN y_user u ON a.from_id=u.id JOIN y_comment b ON a.parent_id=b.parent_id AND a.id>=b.id where a.parent_id IN " +
            "<foreach> item='item' collection='ids' open='(' separator=',' close=')'>#{item}</foreach> " +
            "GROUP BY a.id,a.parent_id HAVING COUNT(b.id) &gt;=3 " +
            "</script>")
    List<Comment> listCommentReply(@Param("ids") List<Integer> ids);

    // 上面那个会爆空指针异常，不知道为什么，最后还是写在了Mapper.xml里了
    List<Comment> treeReplyOfComment(List<Integer> ids);

    // 分页获取某个评论下的回复(子评论)
    @ResultType(Comment.class)
    @Select("SELECT a.id,a.content,a.from_id,a.from_name,a.to_id,a.to_name,a.likes,u.avatar as from_avatar FROM y_comment a\n" +
            "LEFT JOIN y_user u on a.from_id=u.id where a.parent_id=#{parentId} and a.status=1")
    Page<Comment> listReply(@Param("parentId") int parentId, Page<Comment> page);


    // 分页获取某个资源下的评论
    @ResultType(Comment.class)
    @Select("SELECT a.id,a.content,a.from_id,a.from_name,a.likes,u.avatar as from_avatar,IfNULL(b.count,0) as children_count FROM y_comment a\n" +
            "LEFT JOIN (SELECT z.id,z.parent_id ,count(z.id) as count FROM y_comment z  group by z.parent_id) b ON a.id=b.parent_id\n" +
            "LEFT JOIN y_user u on a.from_id=u.id \n" +
            "where a.parent_id=0 and a.status=1 and item_type=#{type} and item_id=#{id}")
    Page<Comment> listComment(@Param("type") int item_type, @Param("id") int item_id, Page<Comment> page);
}
