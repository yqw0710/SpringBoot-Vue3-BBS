package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("注册传输对象")
@Data
public class RegisterDto {

    @ApiModelProperty(required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

/*    @NotBlank(message = "重复的密码不能为空")
    private String repeatPwd;

    @NotBlank(message = "验证码不能为空")
    private String code;*/
}
