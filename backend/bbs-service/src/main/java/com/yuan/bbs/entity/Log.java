package com.yuan.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作记录表
 * </p>
 *
 * @author yuan
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_log")
@ApiModel(value = "Log对象", description = "操作记录表")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "操作类型1登录，2修改密码......")
    private Integer type;

    @ApiModelProperty(value = "操作描述比如：修改密码，修改信息，登录了之类的")
    private String state;

    @ApiModelProperty(value = "ip地址，这么长是为了保存ipv6")
    private String requestIp;

    @ApiModelProperty(value = "ip对应的物理地址,需要调用网上接口获取")
    private String requestAddr;

    private LocalDateTime gmtCreate;


}
