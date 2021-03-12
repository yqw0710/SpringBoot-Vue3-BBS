package com.yuan.bbs.common.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 用户操作类型枚举
 */
@Getter
@ApiModel("用户操作类型枚举")
public enum OptType {
    UNSPECIFIED(-1, "未指定"),
    REGISTERED(0, "注册"),
    Login(1, "登录"),
    POSTED(2, "发帖"),
    COMMENT(3, "评论"),
    TWEET(4, "发推"),
    DELETE_COMMENT(5, "删除评论"),
    MAKE_COMMENT(6, "发表评论");

    private final int type;
    private final String desc;

    OptType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
