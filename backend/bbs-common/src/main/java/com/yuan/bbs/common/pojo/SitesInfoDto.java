package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

// TODO  重构签到功能再弄
@ApiModel("网站相关信息")
@Data
@Accessors(chain = true)
public class SitesInfoDto {
    //    List<com.yuan.common.dto.SignInfoDto> allSignRank;
//    List<com.yuan.common.dto.SignInfoDto> nowSignRank;
    @ApiModelProperty("今日签到人数")
    Long dailyCount;
    @ApiModelProperty("今月签到人数")
    Long monthlyCount;
}
