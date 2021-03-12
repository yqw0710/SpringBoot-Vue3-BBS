package com.yuan.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.common.pojo.ChatRecordDto;
import com.yuan.bbs.entity.Chat;

import java.util.List;

/**
 * <p>
 * 聊天记录表 服务类
 * </p>
 *
 * @author yuan
 * @since 2021-02-08
 */
public interface ChatService extends IService<Chat> {

    List<ChatRecordDto> selectPage(Integer uid);

    IPage<Chat> selectChatHistoryPage(Integer sid, Integer rid, Integer num, Integer size, Boolean readAll);

    void setReadedTrue(Integer sid, Integer rid);
}
