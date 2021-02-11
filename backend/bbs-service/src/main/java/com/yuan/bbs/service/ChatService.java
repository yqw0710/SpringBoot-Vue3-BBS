package com.yuan.bbs.service;

import com.yuan.bbs.common.pojo.ChatRecordDto;
import com.yuan.bbs.entity.Chat;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
