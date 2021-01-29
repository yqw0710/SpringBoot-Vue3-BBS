package com.yuan.bbs.common.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 资源类型枚举
 */
@Getter
@ApiModel("资源状态枚举")
public enum ItemStatus {
    CANCEL(0, "取消操作"),
    NORMAL(1, "正常"),
    FORBID(2, "禁止访问"),
    DELETED(3, "已删除");

    private final int status;
    private final String desc;

    ItemStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
