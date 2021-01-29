package com.yuan.bbs.service;


public interface LikeService {
    /**
     * 判断uid是否点过(type)id
     *
     * @param type 资源类型
     * @param id   被点赞的id
     * @param uid  点赞的uid
     * @return true 点过 false 没点过
     */
    boolean getLikeStatus(Integer type, Integer id, Integer uid);

    /**
     * 获取某个资源在redis中的赞数
     */
    int getLikeCountByType(Integer type, Integer id);

    /**
     * 点赞保存记录到redis,并增加总条数,取消点赞类似
     */
    void like(Integer type, Integer id, Integer uid);

    /**
     * 取消点赞,操作先缓存到redis,
     */
    void unlike(Integer type, Integer id, Integer uid);

    /**
     * 等待实现 不喜欢按钮，设置status为2
     * 如果点过喜欢，要去count那减一(就是调用下unlike).没点过喜欢 那点hate不影响总赞数
     *
     * @param like 是否点过赞
     */
    void hate(Integer type, Integer id, Integer uid, Boolean like);


    /**
     * 获取用户点赞记录来提前渲染点赞按钮的样式 uid type 来获取一个list
     *
     * @param type 资源类型
     */
    Integer[] getUserLikes(Integer uid, Integer type);


}
