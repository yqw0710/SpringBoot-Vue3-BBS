package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("文章传输对象")
@Data
public class ArticleDto {

    @ApiModelProperty(required = true)
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(required = true)
    @NotBlank(message = "内容不能为空")
    private String content;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("文章id")
    private Integer id;

    @ApiModelProperty("标签")
    private String tags;

    @ApiModelProperty("难度")
    private Integer difficulty;

    @ApiModelProperty("文章首图")
    private String firstPic;


}
