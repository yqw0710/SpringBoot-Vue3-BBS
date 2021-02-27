package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("聊天记录传输对象")
@Data
public class ChatRecordDto {

    // 未读消息数量
    Integer unread;
    String content;
    LocalDateTime created;
    //  消息发送者的id
    Integer fromId;
    String nickname;
    String avatar;

}
