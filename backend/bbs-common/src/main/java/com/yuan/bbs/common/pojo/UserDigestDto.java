package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("用户相关摘要信息")
@Data
@Accessors(chain = true)
public class UserDigestDto {
    Integer uid;
    String nickname;
    String avatar;
    String sign;
    Integer sex;
    String coverImg;
}
