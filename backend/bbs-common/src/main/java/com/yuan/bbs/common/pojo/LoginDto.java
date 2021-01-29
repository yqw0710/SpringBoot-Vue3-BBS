package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("登录传输对象")
@Data
public class LoginDto {


    @ApiModelProperty(required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(required = true)
    @NotBlank(message = "密码不能为空")
    private String password;
}