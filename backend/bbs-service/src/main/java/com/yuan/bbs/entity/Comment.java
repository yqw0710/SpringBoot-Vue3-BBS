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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author yuan
 * @since 2020-07-19
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_comment")
@ApiModel(value = "Comment对象", description = "评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(value = "挂载的父评论id,可以用于判断父/子评论")
    private Integer parentId;

    @NotNull
    @ApiModelProperty(value = "被评论的资源id 可以是翻译、文章、资源等  便于根据资源 id 查找该资源下的所有评论")
    private Integer itemId;

    @NotNull
    @ApiModelProperty(value = "被评论的资源的类型,对翻译评论对帖子评论等其他资源评论,待用枚举约定对应关系")
    private Integer itemType;

    @NotNull
    @ApiModelProperty(value = "评论发起者的id", hidden = true)
    private Integer fromId;

    @NotNull
    @ApiModelProperty(value = "评论发起者当时的昵称,先由前端发送")
    private String fromName;

    @ApiModelProperty(value = "被评论的人id")
    private Integer toId;

    @ApiModelProperty(value = "被评论人当时的昵称")
    private String toName;

    @ApiModelProperty(value = "该条评论收获的赞数", hidden = true)
    private Integer likes;


    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value = "评论内容,有字数限制,不能为空")
    private String content;

    @ApiModelProperty(value = "当前评论状态,1是正常 2是被隐藏(可能是讲脏话或者敏感话题) 3是被删除")
    private Integer status;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtCreate;
    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtModified;

    // ////////////////////////////////////////////////////////////

    //子评论列表 使用注解忽视字段 或者改成mybatis的List<Records>,就可以省略childrenCount
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<Comment> children;
    //子评论总数 使用注解忽视字段
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Long childrenCount;

    //评论者头像
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private String fromAvatar;


}
