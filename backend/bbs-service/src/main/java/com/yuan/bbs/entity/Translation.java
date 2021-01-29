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
 * 翻译表
 * </p>
 *
 * @author yuan
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_translation")
@ApiModel(value = "Translation对象", description = "翻译表")
public class Translation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "被翻译文章id", required = true)
    private Integer aid;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer uid;

    @ApiModelProperty(value = "用户昵称", required = true)
    private String uname;

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "翻译正文", required = true)
    private String content;

    @ApiModelProperty(value = "收获赞数", hidden = true)
    private Integer likes;

    @ApiModelProperty(value = "1,正常,2.隐藏,3删除")
    private Integer status;

    @ApiModelProperty(value = "收获的评论数", hidden = true)
    private Integer comments;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtModified;


}
