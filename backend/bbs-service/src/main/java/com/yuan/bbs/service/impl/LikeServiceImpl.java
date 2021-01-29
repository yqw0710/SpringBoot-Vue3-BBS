package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bbs.common.enums.ItemType;
import com.yuan.bbs.common.utils.RedisKeyUtil;
import com.yuan.bbs.entity.CommentLike;
import com.yuan.bbs.entity.PostLike;
import com.yuan.bbs.entity.TranslationLike;
import com.yuan.bbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 点赞操作服务类
 * 点赞需要分类:1. 点赞翻译 2. 点赞评论
 * 然后点赞成功后发送通知,取消点赞要收回未读的通知
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    TranslationLikeService tls;
    @Autowired
    CommentLikeService cls;
    @Autowired
    PostLikeService pls;
    @Autowired
    NoticeService noticeService;

    /**
     * 获取用户对某资源的点赞状态
     *
     * @param type 资源类型
     * @param id   被点赞的id
     * @param uid  点赞的uid
     * @return true:点赞过 false:没点赞过或取消了
     */
    @Override
    public boolean getLikeStatus(Integer type, Integer id, Integer uid) {
        String statusKey = RedisKeyUtil.getStatusKeyByType(1);
        Integer m = (Integer) redisTemplate.opsForHash().get(statusKey, RedisKeyUtil.uniteToField(uid, id));
        if (m == null) {  //redis里没有 就要去数据库查 返回数据库查到的记录数
            int cnt;
            if (type == ItemType.TRANSLATION.getType()) {
                cnt = tls.count(new QueryWrapper<TranslationLike>().eq("tid", id).eq("uid", uid).eq("status", 1));
            } else if (type == ItemType.COMMENT.getType()) {
                cnt = cls.count(new QueryWrapper<CommentLike>().eq("cid", id).eq("uid", uid).eq("status", 1));
            } else if (type == ItemType.POST.getType()) {
                cnt = pls.count(new QueryWrapper<PostLike>().eq("pid", id).eq("uid", uid).eq("status", 1));
            } else {
                cnt = 0;
            }
            return cnt > 0;
        } else {
            return m != 0;
        }
    }

    /**
     * 获取某资源的在redis中的被点赞数量
     *
     * @param type 资源类型
     * @param id   资源id
     * @return int
     */
    @Override
    public int getLikeCountByType(Integer type, Integer id) {
        try {
            return (int) redisTemplate.opsForHash().get(RedisKeyUtil.getCountKeyByType(type), id);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 点赞:操作redis 并发送通知
     */
    @Override
    public void like(Integer type, Integer id, Integer uid) {
        String statusKey = RedisKeyUtil.getStatusKeyByType(type);
        String countKey = RedisKeyUtil.getCountKeyByType(type);
        //给一个hash表里放一个k-v key是 用户id:资源id | val是状态 0是取消点赞 1是点赞 2个点踩
        redisTemplate.opsForHash().put(statusKey, RedisKeyUtil.uniteToField(uid, id), 1);
        //给一个hash表里的k-v的v增加1 k是被的资源id
        redisTemplate.opsForHash().increment(countKey, id, 1L);
        noticeService.likeNotice(uid, id, type);

    }

    /**
     * 取消点赞并收回未读的通知
     */
    @Override
    public void unlike(Integer type, Integer id, Integer uid) {
        String statusKey = RedisKeyUtil.getStatusKeyByType(type);
        String countKey = RedisKeyUtil.getCountKeyByType(type);
        //给一个hash表里放一个k-v key是 用户id:资源id | val是状态 0是取消点赞 1是点赞 2个点踩
        redisTemplate.opsForHash().put(statusKey, RedisKeyUtil.uniteToField(uid, id), 0);
        //给一个hash表里的k-v的v增加1 k是被的资源id
        redisTemplate.opsForHash().increment(countKey, id, -1L);
        noticeService.cancelLikeNotice(uid, id, type);
    }

    /**
     * 仅评论有hate功能,还是用like表保存,设置状态为2,不影响总赞数,但是如果点过喜欢,redis里count--
     */
    @Override
    public void hate(Integer type, Integer id, Integer uid, Boolean like) {
        if (like) {
            // 如果点过赞的,要先取消点赞
            this.unlike(type, id, uid);
        }
        String statusKey = RedisKeyUtil.getStatusKeyByType(type);
        //给一个hash表里放一个k-v key是 用户id:资源id | val是状态 0是取消点赞 1是点赞 2个点踩
        redisTemplate.opsForHash().put(statusKey, RedisKeyUtil.uniteToField(uid, id), 2);
    }


    /**
     * 获取用户点赞记录来提前渲染点赞按钮的样式 uid type 来获取一个list
     */
    @Override
    public Integer[] getUserLikes(Integer uid, Integer type) {
        if (type == ItemType.TRANSLATION.getType()) {
            return tls.getLikes(uid);
        } else if (type == ItemType.COMMENT.getType()) {
            return cls.getLikes(uid);
        } else if (type == ItemType.POST.getType()) {
            return pls.getLikes(uid);
        } else {
            return null;
        }
    }


}
