package com.yuan.bbs.controller;

import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.entity.Chat;
import com.yuan.bbs.service.ChatService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * 所有/app开头的消息都会在这被处理
 */
@Controller
public class ChatController {

    @Autowired
    ChatService chatService;

    // 实现自由的向任意目的地发送消息，并且订阅此目的地的所有用户都能收到消息。
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @ResponseBody
    @ApiOperation("获取用户聊天记录列表")
    @GetMapping("/chat/record")
    public Result getChatRecord(){
        Integer uid= SecurityUtil.getUserFromSecurity().getId();
        chatService.selectPage(uid);
        return Result.succ();
    }


    @MessageMapping("/talkTo")
    public void talkTo(Chat chat, Principal principal) {
        // 设置发送人的id
        chat.setSidFromPrincipal(principal);
        chat.setCreated(LocalDateTime.now());
        // 保存到数据库作为离线的聊天记录
        chatService.save(chat);
        System.out.println(chat);
        simpMessagingTemplate.convertAndSendToUser(chat.getReceive(), "/queue/chat", chat);
    }
}
