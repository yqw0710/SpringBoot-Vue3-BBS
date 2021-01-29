package com.yuan.bbs.common.utils;


import com.yuan.bbs.common.enums.ItemType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * redis key生成的工具列
 *
 * @author yuan
 */
public class RedisKeyUtil {

    public final static String split = ":";

    // 用户对评论的点赞key
    public final static String HASH_KEY_COMMENT_LIKE_COUNT = "comment_like_count";
    // 评论被点赞的状态key
    public final static String HASH_KEY_COMMENT_LIKE_STATUS = "comment_like_status";
    // 用户对翻译的点赞key
    public final static String HASH_KEY_TRANSLATION_LIKE_COUNT = "translation_like_count";
    // 翻译被点赞的状态key
    public final static String HASH_KEY_TRANSLATION_LIKE_STATUS = "translation_like_status";
    // 用户对帖子的点赞key
    public final static String HASH_KEY_POST_LIKE_COUNT = "post_like_count";
    // 翻译被帖子的状态key
    public final static String HASH_KEY_POST_LIKE_STATUS = "post_like_status";
    // 每月用户签到key的前缀
    public final static String KEY_USER_SIGN_PREFIX = "user_sign";
    // 每月用户的签到记录key   示例user_sign_2_202007:011110111111111...
    public final static String BIT_MAP_KEY_USER_SIGN = KEY_USER_SIGN_PREFIX + "_%d_%s";

    // 每天用户签到数量统计key 操作一个集合给签到过的用户丢到集合里(稳定)
    public final static String ZSET_KEY_DAILY_SIGN = "daily_sign_%s";
    // 每月用户签到排行
    public final static String ZSET_KEY_MONTHLY_SIGN = "monthly_sign_%s";
    // 每天用户签到数量统计key 操作一个位图,下表为用户的uid设置为1(极端)
    public final static String BIT_MAP_KEY_DAILY_SIGN = "daily_sign_%s";

    /**
     * 把两个id组合成一个分隔符连接的字符串
     *
     * @param likeUserId 点赞发起人
     * @param beLikedId  被点赞的资源id
     * @return {uid}:{xid}
     */
    public static String uniteToField(Integer likeUserId, Integer beLikedId) {
        return likeUserId + split + beLikedId;
    }

    /**
     * 把字段分割成id数组
     *
     * @param field 用分隔符拼接的字符串
     * @return 数组
     */
    public static int[] splitField(String field) {
        String[] split = field.split(RedisKeyUtil.split);
        return new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
    }

    /**
     * 根据点赞的类型获取状态key
     */
    public static String getStatusKeyByType(ItemType type) {
        return getStatusKeyByType(type.getType());
    }

    public static String getStatusKeyByType(Integer type) {
        String key;
        if (type == ItemType.TRANSLATION.getType()) {
            key = HASH_KEY_TRANSLATION_LIKE_STATUS;
        } else if (type == ItemType.COMMENT.getType()) {
            key = HASH_KEY_COMMENT_LIKE_STATUS;
        } else if (type == ItemType.POST.getType()) {
            key = HASH_KEY_POST_LIKE_STATUS;
        } else {
            key = null;
        }
        return key;
    }

    public static String getCountKeyByType(ItemType type) {
        return getCountKeyByType(type.getType());
    }

    /**
     * 根据点赞的类型获取计数key
     *
     * @param type 赞时有翻译,评论
     * @return
     */
    public static String getCountKeyByType(Integer type) {
        String key;
        if (type == ItemType.TRANSLATION.getType()) {
            key = HASH_KEY_TRANSLATION_LIKE_COUNT;
        } else if (type == ItemType.COMMENT.getType()) {
            key = HASH_KEY_COMMENT_LIKE_COUNT;
        } else if (type == ItemType.POST.getType()) {
            key = HASH_KEY_POST_LIKE_COUNT;
        } else {
            key = null;
        }
        return key;
    }

    /**
     * 获取每个用户签到记录key
     *
     * @param uid
     * @param date
     * @return
     */
    public static String getUserSignKey(Integer uid, LocalDate date) {
        return String.format(BIT_MAP_KEY_USER_SIGN, uid, formatDate(date));
    }


    public static String getDailySign(LocalDate date) {
        return String.format(ZSET_KEY_DAILY_SIGN, date.getDayOfMonth());
    }

    public static String getMonthlySign(LocalDate date) {
        return String.format(ZSET_KEY_MONTHLY_SIGN, formatDate(date));
    }

    private static String formatDate(LocalDate date) {
        return formatDate(date, "yyyy_MM");
    }

    private static String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

}
