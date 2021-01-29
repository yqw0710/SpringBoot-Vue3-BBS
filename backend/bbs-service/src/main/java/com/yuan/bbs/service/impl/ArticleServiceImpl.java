package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.pojo.ArticleDto;
import com.yuan.bbs.entity.Article;
import com.yuan.bbs.mapper.ArticleMapper;
import com.yuan.bbs.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 文章表 服务实现类 未缓存
 * </p>
 *
 * @author yuan
 * @since 2020-07-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Override
    public IPage<Article> selectPage(Integer num, Integer size, String search) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        if (StringUtils.hasText(search)) {
            queryWrapper.like("content", search);
        }
        Page<Article> page = new Page<>(num, size);
        return this.page(page, queryWrapper);
    }

    @Override
    public void edit(ArticleDto articleDto, Integer uid) {
        Integer aid = articleDto.getId();
        Article article;
        if (aid == null || aid < 1) {  // 发布模式
            article = new Article();
            article.setUid(uid);
        } else {            // 编辑模式,得先判断该文章的uid跟操作的id是否相同
            article = this.getById(aid);
            Assert.isTrue(article.getUid().equals(uid), "无操作权限");
            article.setGmtModified(LocalDateTime.now());//虽然mysql那边有设置更新时间戳,但是没有生效
        }
        BeanUtils.copyProperties(articleDto, article);

        this.saveOrUpdate(article);
    }


    @Override
    public void addPv(Integer aid) {
        this.update(new UpdateWrapper<Article>().eq("id", aid).setSql("pv=pv+1"));
    }

    @Override
    public void addTranslations(Integer aid) {
        update(new UpdateWrapper<Article>().setSql("translations=translations+1").eq("id", aid));
    }
}
