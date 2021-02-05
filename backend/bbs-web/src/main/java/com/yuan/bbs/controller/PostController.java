package com.yuan.bbs.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.common.pojo.PostDto;
import com.yuan.bbs.entity.Post;
import com.yuan.bbs.service.PostService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Api(tags = "帖子模块")
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @PermitAll
    @ApiOperation("分页获取帖子")
    @ApiImplicitParam(name = "category", value = "帖子的分类:不填为默认." +
            "其他比如提问(question),分享(share),建议(advice),公告(notice),讨论(discuss )")
    @GetMapping("/page")
    public Result getPostList(Integer num, Integer size, String category) {
        Page<Post> postPage = postService.getPostList(num, size, category);
        return Result.succ(postPage);
    }

    @ApiOperation("发布/编辑一篇帖子")
    @PostMapping("/commit")
    public Result publish(@Validated @RequestBody PostDto postDto) {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        postService.edit(postDto, uid);
        return Result.succ();
    }

    @PermitAll
    @ApiOperation("获取一篇帖子详细内容")
    @GetMapping("/{pid}")
    public Result getOne(@PathVariable Integer pid) {
        Post post = postService.getPostByIDAndAddPv(pid);
        return Result.succ(post);
    }

}
