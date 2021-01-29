package com.yuan.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 测试用表
 * </p>
 *
 * @author yuan
 * @since 2021-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("y_test")
@ApiModel(value = "Test对象", description = "测试用表")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal money;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;


}
