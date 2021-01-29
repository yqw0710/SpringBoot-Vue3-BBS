package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bbs.common.pojo.CollectionDto;
import com.yuan.bbs.entity.Collection;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    // 获取翻译的收藏
    @ResultType(CollectionDto.class)
    @Select("select c.id,c.item_id,c.item_type, t.title,c.gmt_create  " +
            "from y_collection c,y_translation t " +
            "where  c.item_type =1 and c.item_id= t.id and c.uid= #{uid} ")
    Page<CollectionDto> getUserTranCollection(@Param("uid") int uid, Page<CollectionDto> page);

    // 获取帖子的收藏
    @ResultType(CollectionDto.class)
    @Select("select c.id,c.item_id,c.item_type, t.title,c.gmt_create  " +
            "from y_collection c,y_post t " +
            "where  c.item_type =4 and c.item_id = t.id and c.uid= #{uid} ")
    Page<CollectionDto> getUserPostCollection(@Param("uid") int uid, Page<CollectionDto> page);
}
