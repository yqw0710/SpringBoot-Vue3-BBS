package com.yuan.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.entity.Message;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-20
 */
public interface MessageService extends IService<Message> {
    /**
     * 分页查询父评论同时携带2条子评论,耗时有点高..
     */
    IPage<Message> selectPageParent(Integer num, Integer size);

    /**
     * 用于父评论下分页加载更多子评论
     */
    IPage<Message> selectPageChildren(Integer pid, Integer num, Integer size);

    void save(Message message, HttpServletRequest request);

}
