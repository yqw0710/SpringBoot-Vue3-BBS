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
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 留言表
 * </p>
 *
 * @author yuan
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_message")
@ApiModel(value = "Message对象", description = "留言表")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "留言时的昵称")
    private String nickname;

    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "头像", hidden = true)
    private String avatar;

    @ApiModelProperty(value = "父留言id")
    private Integer parentId;

    @ApiModelProperty(value = "登录用户的留言会记录id,便于标记身份", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "如果是回复登录用户的留言,那么就会记录")
    private Integer toId;

    @ApiModelProperty(value = "被回复人的昵称")
    private String toName;

    @ApiModelProperty(value = "留言状态", hidden = true)
    private Integer status;

    @ApiModelProperty(value = "发布留言时的浏览器", hidden = true)
    private String browser;

    @ApiModelProperty(value = "操作系统", hidden = true)
    private String os;

    @ApiModelProperty(value = "ip地址,前端不展示", hidden = true)
    private String ip;

    @ApiModelProperty(value = "时间", hidden = true)
    private LocalDateTime gmtCreate;

    // ////////////////////////////////////////////////////////////

    //子评论列表 使用注解忽视字段
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<Message> children;


    //子评论总数 使用注解忽视字段
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Integer childrenCount;

}
