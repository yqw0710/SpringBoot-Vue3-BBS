package com.yuan.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.common.pojo.ArticleDto;
import com.yuan.bbs.entity.Article;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-16
 */
public interface ArticleService extends IService<Article> {

    // 分页查询
    IPage<Article> selectPage(Integer num, Integer size, String search);

    // 编辑or发布一篇文章
    void edit(ArticleDto articleDto, Integer uid);

    //增加访问次数 简易版
    void addPv(Integer aid);

    // 增加文章翻译数
    void addTranslations(Integer aid);

}
