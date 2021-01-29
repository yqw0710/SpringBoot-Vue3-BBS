package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.entity.TranslationLike;
import com.yuan.bbs.mapper.TranslationLikeMapper;
import com.yuan.bbs.service.TranslationLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文章点赞表 服务实现类
 * </p>
 * https://cron.qqe2.com/ cron 表达式生成
 *
 * @author yuan
 * @since 2020-07-18
 */
@Service
public class TranslationLikeServiceImpl extends ServiceImpl<TranslationLikeMapper, TranslationLike> implements TranslationLikeService {

    @Resource
    TranslationLikeMapper mapper;

    public void saveOrUpdateStatus(Integer id, Integer uid, Integer status) {
        // 关键代码: 数据库表有设置联合unique,防止插入资源id和用户id同时相同的情况
        // @Insert("insert into y_translation_like(`tid`, `uid`, `status`) VALUES (#{tid}, #{uid}, #{status }) on duplicate key update status=#{status}")
        // 没试过自带的saveOrUpdate
        mapper.insertOrUpdateStatus(id, uid, status);
    }

    @Override
    public Integer[] getLikes(Integer uid) {
        return mapper.getUserLikes(uid);
    }
}
