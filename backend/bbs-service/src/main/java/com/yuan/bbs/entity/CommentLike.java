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
 * 文章点赞表
 * </p>
 *
 * @author yuan
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_comment_like")
@ApiModel(value = "CommentLike对象", description = "文章点赞表")
public class CommentLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "被资源的id")
    private Integer cid;

    @ApiModelProperty(value = "点赞人的id")
    private Integer uid;

    @ApiModelProperty(value = "点赞状态0取消|1点赞|2讨厌")
    private Integer status;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtModified;


}
