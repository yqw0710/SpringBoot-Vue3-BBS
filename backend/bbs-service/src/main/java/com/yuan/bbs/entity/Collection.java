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
 * 收藏表
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_collection")
@ApiModel(value = "Collection对象", description = "收藏表")
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "被收藏资源的id")
    private Integer itemId;

    @ApiModelProperty(value = "被收藏资源的类型")
    private Integer itemType;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtCreate;


}
