package com.yuan.bbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.entity.UserFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户上传文件记录表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-24
 */
public interface UserFileService extends IService<UserFile> {
    UserFile getFileRecord(String url);

    String saveFile(MultipartFile file, Integer uid, HttpServletRequest request);

    String saveAvatar(MultipartFile file, Integer uid, HttpServletRequest request);

    String saveCover(MultipartFile file, Integer uid, HttpServletRequest request);

    String saveImageAndCompress(MultipartFile file, HttpServletRequest request, Boolean useOSS, Boolean isCompress);
}
