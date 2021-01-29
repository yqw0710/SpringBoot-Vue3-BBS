package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("收藏数据")
@Data
public class CollectionDto {
    private Integer id;
    private Integer itemId;
    private Integer itemType;
    private String title;
    private LocalDateTime gmtCreate;
}
