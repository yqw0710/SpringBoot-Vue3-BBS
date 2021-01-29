package com.yuan.bbs.common.interfaces;

/**
 * 资源点赞表(中间表)服务类要继承的接口
 */
public interface Like {
    /**
     * 更新/保存用户点赞过资源的状态 xxx=某资源
     * 操作 xxx_like表 比如 y_post_like
     *
     * @param id     xxx的id
     * @param uid    用户id
     * @param status 点赞状态 0 取消 1 点赞 2 讨厌
     */
    void saveOrUpdateStatus(Integer id, Integer uid, Integer status);

    // 获取用户点赞过的资源，用于渲染点赞按钮
    Integer[] getLikes(Integer uid);
}
