package com.yuan.bbs.common.annotation;

import com.yuan.bbs.common.enums.OptType;

import java.lang.annotation.*;

import static com.yuan.bbs.common.enums.OptType.UNSPECIFIED;

/**
 * 标记方法,使其可以配合AOP记录调用日志
 * 使用：@LogRecord | @LogRecord("xxx") |  @LogRecord(type=OptTypeEnum.XXX)
 *
 * @author yuan
 * @since 2021/1/25
 */
@Target(ElementType.METHOD) //方法
@Retention(RetentionPolicy.RUNTIME) //运行时
@Documented
public @interface LogRecord {
    // 操作说明
    String value() default "";

    // 操作类型
    OptType type() default UNSPECIFIED;
}
