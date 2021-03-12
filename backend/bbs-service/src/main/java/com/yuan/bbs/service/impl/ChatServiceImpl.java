package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
     *
     * @param uid 谁的消息
     */
    @Override
    public List<ChatRecordDto> selectPage(Integer uid) {
        return chatMapper.chatRecordList(uid);
    }


    /**
     * @param sid  sender
     * @param rid  receiver
     * @param num  第几页
     * @param size 每页多少
     * @return 分页对象
     */
    @Override
    public IPage<Chat> selectChatHistoryPage(Integer sid, Integer rid, Integer num, Integer size, Boolean readAll) {
        QueryWrapper<Chat> wrapper = new QueryWrapper<>();
        wrapper.eq("sid", sid)
                .eq("rid", rid)
                .or()
                .eq("sid", rid)
                .eq("rid", sid)
                .orderByDesc("created");
        // 获取聊天记录时，可以将消息的接收者和发送者相关的是否阅读置为1
        if (readAll != null && readAll) {
            setReadedTrue(sid, rid);
        }
        return this.page(new Page<>(num, size), wrapper);
    }

    public void setReadedTrue(Integer sid, Integer rid) {
        UpdateWrapper<Chat> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("readed", 1)
                .eq("readed", 0)
                .eq("rid", rid)
                .eq("sid", sid);
        this.update(updateWrapper);
    }
}
