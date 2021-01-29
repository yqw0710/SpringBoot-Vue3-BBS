package com.yuan.bbs.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.common.pojo.ApiTranslationDto;
import com.yuan.bbs.common.pojo.TestDto;
import com.yuan.bbs.entity.Test;
import com.yuan.bbs.service.ApiService;
import com.yuan.bbs.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;

@Api(tags = "基础测试")
@Slf4j
@RestController
public class BaseTestController {

    @Autowired
    TestService testService;
    @Autowired
    ApiService apiService;

    @GetMapping("/test01")
    public Result test01() {
        System.out.println("成功!");
        log.info("Info/信息");
        return Result.succ();
    }

    @GetMapping("/test02")
    public Result test02() {
        return Result.succ(LocalDateTime.now());
    }

    @GetMapping("/test03")
    public Result test03() {
        Test byId = testService.getById(1);
        return Result.succ(byId);
    }

    @GetMapping("/test04")
    @ApiImplicitParam(name = "money", value = "设置1号用户的价钱，0.00~100.00", required = true)
    public Result test04(Float money) {
        UpdateWrapper<Test> wrapper = new UpdateWrapper<>();
        testService.update(wrapper.eq("id", 1).set("money", money));
        return Result.succ(testService.getById(1));
    }

    @PostMapping("/test05")
    public Result test05(@Validated TestDto dto) {
        System.out.println(dto);
        return Result.succ();
    }


    @PermitAll
    @GetMapping("/t1est06")
    public Result test06(String word) {
        ApiTranslationDto transResult = apiService.getTransResult(word);
        return Result.succ(transResult.getTrans_result()[0]);
    }
}
