package com.yuan.bbs.controller;

import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "翻译模块")
@RestController
public class TranslateController {

    @Autowired
    ApiService apiService;

    @ApiOperation("对接的百度通用翻译api")
    @GetMapping("/api/{word}")
    public Result getTrans(@PathVariable String word) {
        return Result.succ(apiService.getTransResult(word).getTrans_result()[0]);
    }

}
