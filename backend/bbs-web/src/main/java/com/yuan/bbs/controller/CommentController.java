package com.yuan.bbs.controller;

import com.yuan.bbs.common.annotation.LogRecord;
import com.yuan.bbs.common.enums.OptType;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.entity.Comment;
import com.yuan.bbs.service.CommentService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Api(tags = "评论功能")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @LogRecord(type = OptType.DELETE_COMMENT)
    @ApiOperation("删除评论")
    @DeleteMapping("/{id}")
    public Result deleteComment(@PathVariable Integer id) {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();

        return Result.succ(commentService.deleteComment(id, uid));
    }

    @LogRecord(type = OptType.MAKE_COMMENT)
    @ApiOperation("发表条评论")
    @PostMapping("/make")
    public Result makeComment(@RequestBody Comment comment) {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        return Result.succ(commentService.saveComment(uid, comment));
    }

    @PermitAll
    @ApiOperation("分页获取某个资源下的评论")
    @GetMapping("/{itemType}/{itemId}")
    public Result getComments(@PathVariable Integer itemType, @PathVariable Integer itemId, Integer num, Integer size) {
        return Result.succ(commentService.listComments(itemType, itemId, num, size));
    }

    @PermitAll
    @ApiOperation("分页获取某个评论下的回复")
    @GetMapping("/reply/{pid}")
    public Result getReplyOfComment(@PathVariable Integer pid, Integer num, Integer size) {
        return Result.succ(commentService.listReplyOfComment(pid, num, size));
    }

}
