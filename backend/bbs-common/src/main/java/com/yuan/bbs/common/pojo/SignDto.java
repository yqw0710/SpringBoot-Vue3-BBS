package com.yuan.bbs.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@ApiModel("签到信息")
public class SignDto {

    @ApiModelProperty("本月签到次数")
    Integer signCount;
    @ApiModelProperty("今日签到排名")
    Long signRank;
    @ApiModelProperty("本次签到得分")
    Integer signPoint;
    @ApiModelProperty("本月签到情况")
    String signInfo;
    @ApiModelProperty("本月首次签到日期")
    LocalDate FirstSignDate;
    @ApiModelProperty("操作结果")
    private boolean signResult;
    @ApiModelProperty(value = "用户连续签到天数,用于排序")
    private Integer signContNow;
    @ApiModelProperty(value = "用户总的签到次数")
    private Integer signContAll;
    @ApiModelProperty(value = "用户最长签到天数,用于排序")
    private Integer signContMax;
}
