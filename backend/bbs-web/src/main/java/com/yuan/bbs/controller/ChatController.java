package com.yuan.bbs.controller;

import com.yuan.bbs.common.lang.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * 所有/app开头的消息都会在这被处理
 */
@Controller
public class ChatController {

    // 实现自由的向任意目的地发送消息，并且订阅此目的地的所有用户都能收到消息。
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    // 订阅测试 服务端->客户端
    @SubscribeMapping("/subscribe")
    public String handleSubscribe(StompHeaderAccessor stompHeaderAccessor) {
        System.out.println(stompHeaderAccessor.getSessionAttributes());
        System.out.println("有人订阅了");
        return "订阅成功！";
    }

    // 发送消息测试 客户端->服务端->客户端
    @MessageMapping("/greeting")
    public String handle(Principal principal, ChatMessage msg) {
        System.out.println(msg);
        System.out.println("principal.name:" + principal.getName());
        return "greeting";
    }

    // 单聊测试 客户端A->客户端B
    @MessageMapping("/chatTo")
    public void singleUser(ChatMessage msg, StompHeaderAccessor stompHeaderAccessor) {
        System.out.println(msg);
        // String username = (String) stompHeaderAccessor.getSessionAttributes().get("username");
        simpMessageSendingOperations.convertAndSendToUser(msg.getReceiver(), "/queue/chat", msg);
    }

    // 群聊测试(单个聊天室) 客户端->所以客户端
    @MessageMapping("/chats")
    public void chatRoom(ChatMessage msg) {
        System.out.println("群聊");
        simpMessageSendingOperations.convertAndSend("/topic/public", msg);
    }

}
