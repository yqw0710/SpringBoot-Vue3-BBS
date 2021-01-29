package com.yuan.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author yuan
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_userinfo")
@ApiModel(value = "Userinfo对象", description = "用户信息表")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联的用户id")
    private Integer uid;

    @ApiModelProperty(value = "个性签名")
    private String sign;

    @ApiModelProperty(value = "性别 1男2女3保密")
    private Integer sex;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "qq号")
    private String qqnum;

    @ApiModelProperty(value = "封面背景图")
    private String coverImg;

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "专业")
    @TableField("Profession")
    private String Profession;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtModified;


}
