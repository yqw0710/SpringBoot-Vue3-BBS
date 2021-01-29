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
 * 用户每月签到表
 * </p>
 *
 * @author yuan
 * @since 2020-07-26
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_sign")
@ApiModel(value = "Sign对象", description = "用户每月签到表")
public class Sign implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer uid;
    @ApiModelProperty(value = "用户签到记录,redis落库时保存 例[2020/07]001100-[2020/08]001101")
    private String signRecord;
    @ApiModelProperty(value = "用户连续签到记录")
    private String signContData;
    @ApiModelProperty(value = "用户连续签到天数,用于排序")
    private Integer signContNow;
    @ApiModelProperty(value = "用户总的签到次数")
    private Integer signContAll;
    @ApiModelProperty(value = "用户最长签到天数,用于排序")
    private Integer signContMax;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
