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
 * 通知表
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_notice")
@ApiModel(value = "Notice对象", description = "通知表")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(value = "通知者的id")
    private Integer notifier;

    @ApiModelProperty(value = "接收者的id")
    private Integer receiver;

    @ApiModelProperty(value = "通知的类型/行为  1是系统通知 2是点赞通知 3是收藏通知 4是评论资源通知")
    private Integer type;

    @ApiModelProperty(value = "通知标题")
    private String title;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "通知状态 0未读 1已读")
    private Integer status;

    @ApiModelProperty(value = "当通知消息关联的是一个资源时,需要指定资源的id和类型,方便跳转")
    private Integer itemId;

    @ApiModelProperty(value = "当通知消息关联的是一个资源时,需要指定资源的id和类型,方便跳转")
    private Integer itemType;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtModified;


}
