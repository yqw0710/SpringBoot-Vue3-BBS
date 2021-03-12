package com.yuan.bbs.common.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@Getter
@ApiModel("结果枚举异常")
public enum ResultCode {
    SUCCESS(200, "操作成功"),

    //    #1000~1999 区间表示参数错误
    UNKNOWN_EXCEPTION(1000, "未定义异常"),
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_VALID_EXCEPTION(1002, "参数校验失败"),

    //    #2000~2999 区间表示用户错误
    NO_VALID_TOKEN(2001, "无效的令牌"),
    USER_NOT_EXISTS(2002, "用户不存在"),
    PERMISSION_DENIED(2003, "当前权限不足"),
    USER_ALREADY_EXISTS(2004, "用户已经存在"),
    EMAIL_ALREADY_EXISTS(2005, "邮箱已经存在"),
    USER_LOGIN_FAILED(2006, "用户不存在或密码错误"),
    USERDATA_UPDATE_FAILED(2007, "用户数据更新失败"),

    //    #3000~3999 区间表示sql错误
    SQL_EXCEPTION(3001, "sql异常"),
    SQL_DuplicateKey_EXCEPTION(3002, "字段值重复");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
