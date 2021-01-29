package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "TestDto", description = "测试用dto类，进行数据校验")
public class TestDto {
    @NotBlank(message = "地址不能为空")
    String name;
    @Max(value = 1500, message = "金额不能超过1500")
    Float money;
}
