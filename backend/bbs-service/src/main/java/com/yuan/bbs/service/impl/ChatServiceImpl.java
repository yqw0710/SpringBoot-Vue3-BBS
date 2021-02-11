package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.pojo.ChatRecordDto;
import com.yuan.bbs.entity.Chat;
import com.yuan.bbs.mapper.ChatMapper;
import com.yuan.bbs.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 聊天记录表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2021-02-08
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Resource
    ChatMapper chatMapper;

    /**
     * 之后再添加分页功能 传入 num和size，构建mybatis-plus的IPage对象传给mapper层
     * @param uid  谁的消息
     */
    @Override
    public List<ChatRecordDto> selectPage(Integer uid) {
        return chatMapper.chatRecordList(uid);
    }
}
