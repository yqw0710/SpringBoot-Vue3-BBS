package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("用户签到次数")
@Data
public class SignInfoDto {
    Integer uid;
    String avatar;
    String nickname;
    Integer times;
}
