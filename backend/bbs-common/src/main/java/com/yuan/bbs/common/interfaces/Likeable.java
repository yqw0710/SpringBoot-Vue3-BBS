package com.yuan.bbs.common.interfaces;

/**
 * 可点赞的资源要继承的接口
 */
public interface Likeable {


    /**
     * 更新 资源的赞数
     * 操作 某个资源表 比如 y_comment,y_post
     *
     * @param id    资源id
     * @param likes 在redis的时间中获得的赞数
     */
    void updateLikes(int id, int likes);
}
