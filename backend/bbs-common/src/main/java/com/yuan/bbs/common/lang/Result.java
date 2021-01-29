package com.yuan.bbs.common.lang;

import com.yuan.bbs.common.enums.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一结果封装
 * 跟前端交互的数据格式
 * 在Controller使用Result的静态方法进行返回
 * 例如：return Result.succ(obj); 或者 return Result.fail(ResultCode.XXX_XXX);
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;


    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }


    public static Result succ() {
        return new Result(ResultCode.SUCCESS, null);
    }

    public static Result succ(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }


    public static Result fail(ResultCode resultCode) {
        return new Result(resultCode, null);
    }

    public static Result fail(ResultCode resultCode, Object data) {
        return new Result(resultCode, data);
    }

    /**
     * 随性的设置成功的Code和消息
     */
    public static Result succ(int code, String msg, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }

    /**
     * 随性的设置失败的Code和消息
     */
    public static Result fail(int code, String msg, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(msg);
        return m;
    }

}