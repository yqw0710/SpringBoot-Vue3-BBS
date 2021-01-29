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
 * 文章表
 * </p>
 *
 * @author yuan
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_article")
@ApiModel(value = "Article对象", description = "文章表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "发布人id")
    private Integer uid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "正文")
    private String content;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "难度等级1小学2初中3高中4大学5很难...")
    private Integer difficulty;

    @ApiModelProperty(value = "1,正常,2.隐藏,3删除")
    private Integer status;

    @ApiModelProperty(value = "页面浏览数")
    private Integer pv;

    @ApiModelProperty(value = "收藏数")
    private Integer collections;

    @ApiModelProperty(value = "翻译数")
    private Integer translations;

    @ApiModelProperty(value = "文章首图")
    private String firstPic;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
