package com.yuan.bbs.controller;

import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.entity.UserFile;
import com.yuan.bbs.service.UserFileService;
import com.yuan.bbs.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "文件操作")
@RestController
@RequestMapping("/file")
public class UserFileController {

    @Autowired
    UserFileService userFileService;


    @ApiOperation("上传图片")
    @PostMapping("/img")
    public Result uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String url = userFileService.saveImageAndCompress(file, request, true, true);
        return Result.succ(url);
    }

    @ApiOperation("上传用户空间背景")
    @PostMapping("/cover")
    public Result uploadCover(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Integer id = SecurityUtil.getUserFromSecurity().getId();
        String url = userFileService.saveCover(file, id, request);
        return Result.succ(url);
    }

    @ApiOperation("上传头像")
    @PostMapping("/avatar")
    public Result setAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Integer id = SecurityUtil.getUserFromSecurity().getId();
        String url = userFileService.saveAvatar(file, id, request);
        return Result.succ(url);
    }

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Integer id = SecurityUtil.getUserFromSecurity().getId();
        String url = userFileService.saveFile(file, id, request);
        return Result.succ(url);
    }

    @PermitAll
    @ApiOperation("通过文件url获取文件信息")
    @GetMapping("/info")
    public Result getFileInfo(@RequestParam("url") String url) {
        UserFile uf = userFileService.getFileRecord(url);
        if (uf == null) {
            uf = new UserFile().setId(-1).setOriginalFileName("unknown");
        }
        return Result.succ(uf);
    }
}
