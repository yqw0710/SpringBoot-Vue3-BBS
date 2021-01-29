package com.yuan.bbs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yuan.bbs.common.annotation.LogRecord;
import com.yuan.bbs.common.enums.OptType;
import com.yuan.bbs.common.enums.ResultCode;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.common.pojo.LoginDto;
import com.yuan.bbs.common.pojo.RegisterDto;
import com.yuan.bbs.common.pojo.UserDigestDto;
import com.yuan.bbs.common.pojo.UserDto;
import com.yuan.bbs.entity.User;
import com.yuan.bbs.entity.Userinfo;
import com.yuan.bbs.service.UserService;
import com.yuan.bbs.service.UserinfoService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

/**
 * 用户信息->userinfo表
 * 用户->user表
 */
@Api(tags = "用户功能")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserinfoService userinfoService;

    @ApiOperation("修改用户昵称字段")
    @PostMapping("/update/nickname")
    public Result updateNickname(String val) {
        int uid = SecurityUtil.getUserFromSecurity().getId();
        boolean flag = userService.updateNickname(uid, val);
        return flag ? Result.succ() : Result.fail(ResultCode.USERDATA_UPDATE_FAILED);
    }

    @ApiOperation("修改用户信息指定字段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value =
                    "sex,birthday,qqnum,cover_img,school,profession，" +
                            "其中sex值为[1,2,3]，日期格式为yyyy-MM-dd"),
            @ApiImplicitParam(name = "value", value = "修改后的值"),
    })
    @PutMapping("/update/info")
    public Result updateUserinfo(String key, String value) {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        // 逻辑判断:断言key不是关键字段
        Assert.doesNotContain("id|uid|gmt_create|gmt_modified", key, "无法更新该字段");
        userinfoService.update(new UpdateWrapper<Userinfo>().set(key, value).eq("uid", uid));
        return Result.succ();
    }

    @PermitAll
    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "uid", value = "用户id", required = true)
    @GetMapping("/info/{uid}")
    public Result getUserinfo(@PathVariable Integer uid) {
        Userinfo userinfo = userinfoService.getUserinfo(uid);
        Assert.notNull(userinfo, "用户信息不存在或者设置不可见");
        return Result.succ(userinfo);
    }

    @PermitAll
    @ApiOperation("获取用户的信息摘要")
    @GetMapping("/info/digest/{uid}")
    public Result getUserinfoDigest(@PathVariable Integer uid) {
        UserDigestDto userDigest = userinfoService.getUserDigest(uid);
        Assert.notNull(userDigest, "用户信息摘要不存在或者设置不可见");
        return Result.succ(userDigest);
    }

    @ApiOperation("获取用户最新数据")
    @GetMapping("/newest")
    public Result getNewestData() {
        int uid = SecurityUtil.getUserFromSecurity().getId();
        UserDto dto = userService.getUserNewestData(uid);
        return Result.succ(dto);
    }


    @PermitAll
    @ApiOperation("判断用户/邮箱是否已存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "email | username"),
            @ApiImplicitParam(name = "value", value = "具体待判断的数据")
    })
    @GetMapping("/exist")
    public Result isExist(String type, String value) {
        // 临时写的，还没有邮箱功能呢
        if ("email".equalsIgnoreCase(type)) {
            return !("123456@qq.com".equals(value)) ? Result.succ()
                    : Result.fail(ResultCode.EMAIL_ALREADY_EXISTS);
        } else {
            int cnt = userService.count(new QueryWrapper<User>().eq("username", value));
            return cnt > 0 ? Result.fail(ResultCode.USER_ALREADY_EXISTS) : Result.succ();
        }
    }

    @PermitAll
    @LogRecord(type = OptType.Login)
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto dto) {
        UserDto user = userService.login(dto);
        return user != null ? Result.succ(user) : Result.fail(ResultCode.USER_LOGIN_FAILED);
    }

    @PermitAll
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto dto) {
        UserDto register = userService.register(dto);
        return Result.succ(register);
    }
}
