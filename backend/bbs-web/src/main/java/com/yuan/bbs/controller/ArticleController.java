package com.yuan.bbs.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bbs.common.enums.ItemStatus;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.common.pojo.ArticleDto;
import com.yuan.bbs.entity.Article;
import com.yuan.bbs.service.ArticleService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Api(tags="文章阅读")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PermitAll
    @ApiOperation("获取一篇文章详细内容")
    @GetMapping("/{aid}")
    public Result getOne(@PathVariable Integer aid) {
        Article byId = articleService.getById(aid);
        Assert.isTrue(byId.getStatus() == ItemStatus.NORMAL.getStatus(), "文章已被删除或者被隐藏");
        articleService.addPv(aid);// 简易的增加文章访问量
        return Result.succ(byId);
    }

    /**
     * 分页查询,考虑不返回content字段,不然数据量太大,
     * 还要考虑status,size大小
     * 还有关键词查询 还没搞
     * 还有根据用户id查询
     */
    @PermitAll
    @ApiOperation("文章的分页查询")
    @GetMapping("/page")
    public Result queryList(Integer num, Integer size, String search) {
        IPage<Article> articleIPage = articleService.selectPage(num, size, search);
        return Result.succ(articleIPage);
    }

    @ApiOperation("发布/编辑一篇文章")
    @PostMapping("/commit")
    public Result publish(@Validated @RequestBody ArticleDto articleDto) {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        articleService.edit(articleDto, uid);
        return Result.succ();
    }
    @ApiOperation("修改文章状态")
    @PutMapping("/{aid}")
    public Result setArticleStatus(@PathVariable Integer aid, Integer val) {
        Article article = articleService.getById(aid);
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        Assert.isTrue(article.getUid().equals(uid) && (val > 0 && val < 4), "无操作权限或数值错误");
        articleService.update(new UpdateWrapper<Article>().set("status", val).eq("id", aid));
        return Result.succ();
    }


}
