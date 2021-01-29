package com.yuan.bbs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.entity.Message;
import com.yuan.bbs.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "网站留言")
@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    @ApiOperation("发表一条留言")
    @PostMapping("/message")
    public Result saveMessage(HttpServletRequest request, @Validated @RequestBody Message message) {
        messageService.save(message, request);
        return Result.succ();
    }

    @ApiOperation("分页获取留言")
    @GetMapping("/message")
    public Result getMessages(Integer num, Integer size) {
        IPage<Message> messageIPage = messageService.selectPageParent(num, size);
        return Result.succ(messageIPage);
    }

    @ApiOperation("分页获取子留言")
    @GetMapping("/message/{pid}")
    public Result getMessageChildren(@PathVariable Integer pid, Integer num, Integer size) {
        return Result.succ(messageService.selectPageChildren(pid, num, size));
    }

}
