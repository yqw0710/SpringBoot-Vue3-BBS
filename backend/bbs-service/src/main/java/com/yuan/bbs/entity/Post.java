package com.yuan.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 帖子
 * </p>
 *
 * @author yuan
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode
@TableName("y_post")
@ApiModel(value = "Post对象", description = "帖子")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建者/发布者")
    private Integer uid;

    @ApiModelProperty(value = "帖子标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "标签1-标签2")
    private String tags;

    @ApiModelProperty(value = "n.分类,种类  比如提问,分享,建议,公告,讨论")
    private String category;

    @ApiModelProperty(value = "是否热门 0正常 1热门")
    private Integer hot;

    @ApiModelProperty(value = "状态1正常 2.隐藏 3.删除  4. 关闭交流")
    private Integer status;

    @ApiModelProperty(value = "浏览量")
    private Integer pv;

    @ApiModelProperty(value = "赞数")
    private Integer likes;

    @ApiModelProperty(value = "评论数")
    private Integer comments;

    @ApiModelProperty(value = "收藏数")
    private Integer collections;

    @ApiModelProperty(value = "附件url,可能可以上传附件")
    private String attachment;

    @ApiModelProperty(value = "最后回复时间")
    private LocalDateTime lastReply;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @TableField(exist = false)
    private String avatar;
    @TableField(exist = false)
    private String nickname;
}
