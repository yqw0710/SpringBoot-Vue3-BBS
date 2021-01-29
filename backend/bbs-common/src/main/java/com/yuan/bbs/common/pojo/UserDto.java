package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("登录注册后的传输对象")
public class UserDto {
    Integer uid;
    Integer point;
    String token;
    String username;
    String nickname;
    String avatar;
}
