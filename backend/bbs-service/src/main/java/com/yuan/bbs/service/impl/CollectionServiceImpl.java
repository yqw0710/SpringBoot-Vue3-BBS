package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.enums.ItemType;
import com.yuan.bbs.common.pojo.CollectionDto;
import com.yuan.bbs.entity.Collection;
import com.yuan.bbs.mapper.CollectionMapper;
import com.yuan.bbs.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 收藏表 服务实现类 待关联查询
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Resource
    CollectionMapper mapper;

    @Override
    public IPage<CollectionDto> selectPage(Integer uid, Integer num, Integer size, Integer type) {
        Page<CollectionDto> cs = null;
        // 根据类别获取用户的收藏
        if (type == ItemType.TRANSLATION.getType()) {
            cs = mapper.getUserTranCollection(uid, new Page<>(num, size));
            System.out.println(cs);
        } else if (type == ItemType.POST.getType()) {
            cs = mapper.getUserPostCollection(uid, new Page<>(num, size));
            System.out.println(cs);
        }
        System.out.println(cs);
        return cs;
    }
}
