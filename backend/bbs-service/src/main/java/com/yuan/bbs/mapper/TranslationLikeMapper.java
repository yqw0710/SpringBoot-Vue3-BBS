package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bbs.entity.TranslationLike;
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
 * @since 2020-07-18
 */
public interface TranslationLikeMapper extends BaseMapper<TranslationLike> {

    @Insert("insert into y_translation_like(`tid`, `uid`, `status`) VALUES (#{tid}, #{uid}, #{status }) on duplicate key update status=#{status}")
    void insertOrUpdateStatus(@Param("tid") Integer tid, @Param("uid") Integer uid, @Param("status") Integer status);

    @ResultType(Integer.class)
    @Select("select tid from y_translation_like where uid = #{uid} and status= 1 ")
    Integer[] getUserLikes(Integer uid);
}
