package com.yuan.bbs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.entity.Notice;
import com.yuan.bbs.service.NoticeService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO 创建通知未重构 如收藏 点赞 评论等
@Api(tags = "消息通知")
@RestController
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @ApiOperation("登录状态网站刷新时获取用户未读通知")
    @GetMapping("/notice")
    public Result getUserNotice() {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        List<Notice> list = noticeService.list(new QueryWrapper<Notice>().eq("status", "0").eq("receiver", uid));
        return Result.succ(list);
    }

    /**
     * 将用户通知标记为已读
     *
     * @param id 通知的主键,为0或null则将用户所有通知都已读
     */
    @ApiOperation("已读通知,如果id==0则已读该用户所有未读通知")
    @ApiImplicitParam(name = "id", value = "通知消息的主键")
    @PutMapping("/notice/{id}")
    public Result read(@PathVariable Integer id) {
        Integer uid = SecurityUtil.getUserFromSecurity().getId();
        noticeService.read(id, uid);
        return Result.succ();
    }

}
