package com.yuan.bbs.common.lang;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * websocket用
 * <p>
 * type:消息类型
 * content：消息内容
 * sender：发送者
 * receiver：接收者
 * time：发送时间
 */
@Data
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String receiver;
    private LocalDateTime time;

    @Getter
    public enum MessageType {
        CHAT, //消息
        JOIN, //加入
        LEAVE //离开
    }


}