package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("帖子传输对象")
public class PostDto {

    @ApiModelProperty("文章id")
    private Integer id;

    @ApiModelProperty(required = true)
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(required = true)
    @NotBlank(message = "内容不能为空")
    private String content;

    @ApiModelProperty("标签")
    @NotBlank(message = "标签不能为空")
    private String tags;

    @ApiModelProperty("分类")
    @NotBlank(message = "分类不能为空")
    private String category;

    @ApiModelProperty("附件url")
    private String attachment;


}
