package com.yuan.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.common.pojo.CollectionDto;
import com.yuan.bbs.entity.Collection;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
public interface CollectionService extends IService<Collection> {

    IPage<CollectionDto> selectPage(Integer uid, Integer num, Integer size, Integer type);


}
