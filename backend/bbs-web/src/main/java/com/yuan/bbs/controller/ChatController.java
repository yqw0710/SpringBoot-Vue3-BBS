package com.yuan.bbs.controller;

import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.common.pojo.ChatRecordDto;
import com.yuan.bbs.entity.Chat;
import com.yuan.bbs.service.ChatService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 所有/app开头的消息都会在这被处理
 */
@Api(tags = "聊天功能")
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
    public Result getChatRecord() {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        List<ChatRecordDto> chatRecordDtoList = chatService.selectPage(uid);
        return Result.succ(chatRecordDtoList);
    }

    @ResponseBody
    @ApiOperation("获取用户与联系人的聊天历史记录")
    @GetMapping("/chat/history")
    public Result getChatHistory(Integer sid, Integer num, Integer size, Boolean read) {
        Integer rid = SecurityUtil.getUserFromSecurity().getId();
        return Result.succ(chatService.selectChatHistoryPage(sid, rid, num, size, read));
    }


    @MessageMapping("talk2")
    public void talkTo(Chat chat, Principal principal) {
        System.out.println("talk to!");
        // 设置发送人的id
        chat.setSidFromPrincipal(principal);
        chat.setCreated(LocalDateTime.now());
        // 保存到数据库作为离线的聊天记录
        chatService.save(chat);
        System.out.println(chat);
        simpMessagingTemplate.convertAndSendToUser(chat.getReceive(), "/queue/chat", chat);
    }

    /*
    接入websocket后，用户接到消息后如果阅读了这个消息，可以通过这个方法
    将数据库的中的readed字段置为true(1)，与该用户则无未读消息了
     stomp.send("/app/readed.{xx}");
     */
    @MessageMapping("readed.{sender}")
    public void readedMessage(Principal principal, @DestinationVariable String sender) {
        chatService.setReadedTrue(Integer.parseInt(sender), Integer.parseInt(principal.getName()));
    }
}
