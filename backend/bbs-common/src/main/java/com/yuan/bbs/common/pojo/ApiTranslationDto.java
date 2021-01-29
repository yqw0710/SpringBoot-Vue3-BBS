package com.yuan.bbs.common.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class ApiTranslationDto {
    Map<String, String>[] trans_result;
}
