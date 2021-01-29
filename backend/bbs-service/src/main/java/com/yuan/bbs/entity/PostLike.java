package com.yuan.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 帖子点赞表
 * </p>
 *
 * @author yuan
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode
@TableName("y_post_like")
@ApiModel(value = "PostLike对象", description = "帖子点赞表")
public class PostLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "被资源的id")
    private Integer pid;

    @ApiModelProperty(value = "点赞人的id")
    private Integer uid;

    @ApiModelProperty(value = "点赞状态0取消|1点赞|2讨厌")
    private Integer status;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
