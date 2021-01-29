package com.yuan.bbs.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.utils.QiniuUtil;
import com.yuan.bbs.entity.User;
import com.yuan.bbs.entity.UserFile;
import com.yuan.bbs.entity.Userinfo;
import com.yuan.bbs.mapper.UserFileMapper;
import com.yuan.bbs.service.UserFileService;
import com.yuan.bbs.service.UserService;
import com.yuan.bbs.service.UserinfoService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户上传文件记录表 服务实现类
 * </p>
 * 主要是用来学习的,写了一些不重要的代码
 *
 * @author yuan
 * @since 2020-07-24
 */
@Slf4j
@Service
public class UserFileServiceImpl extends ServiceImpl<UserFileMapper, UserFile> implements UserFileService {

    @Value("${file-save-path}")
    private String fileSavePath;
    @Autowired
    private QiniuUtil qiniuUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserinfoService userinfoService;

    private static final String FOLDER_NAME = "images/";
    //支持的图片类型
    private static final List<String> CONTENT_TYPE = Arrays.asList("image/gif", "image/jpeg", "image/png");

    /**
     * 保存文件到服务器指定路径上。”随便写写！反正以后用的对象存储服务，不放服务器上的。“
     * 并根据文件后缀名进行归档和记录详细信息到数据库中
     */
    @Override
    public String saveFile(MultipartFile file, Integer uid, HttpServletRequest request) {
        // 文件md5作为文件名
        String fileMd5;
        try {
            fileMd5 = SecureUtil.md5(file.getInputStream());
            UserFile existFile = getOne(new UpdateWrapper<UserFile>().eq("md5", fileMd5).last("limit 1"), false);
            if (existFile != null) return existFile.getUrl();
            String originalFilename = file.getOriginalFilename();
            String uniqueFileName = fileMd5 + originalFilename;
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String suffix1 = suffix + "/";
            // 文件保存的位置
            String filePath = fileSavePath + suffix1 + uniqueFileName;
            // 文件web浏览路径
            System.out.println(request.getRequestURI());
            System.out.println(request.getRequestURI());
            String urlPath = request.getScheme() + "://"
                    + request.getServerName() + ":"
                    + request.getServerPort()
                    + request.getContextPath() + "/archive/"
                    + suffix1 + uniqueFileName;
            // 封装实体对象
            UserFile userFile = new UserFile();
            userFile.setOriginalFileName(originalFilename).setNewFileName(uniqueFileName)
                    .setFileSize(file.getSize()).setFileSuffix(suffix)
                    .setUrl(urlPath).setUid(uid).setMd5(fileMd5);
            File file1 = new File(filePath);
            if (!file1.exists()) file1.mkdirs(); // 要是目录不存在,创建一个,linux中需要权限
            file.transferTo(file1);
            this.save(userFile);
            return urlPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserFile getFileRecord(String url) {
        UserFile uf = this.getOne(new QueryWrapper<UserFile>().eq("url", url), false);
        System.out.println(uf);
        return uf;
    }

    /**
     * 保存图片到对象存储服务中，而不是本地。
     * 压缩图片的话要保存两份
     * xxx.xxx/文件名 访问原图
     * xxx.xxx/文件名-small访问缩略图
     */
    public String saveImageAndCompress(MultipartFile file, HttpServletRequest request, Boolean useOSS, Boolean isCompress) {
        String filePath = fileSavePath + FOLDER_NAME;
        String imgMd5;
        String imgNameCompressed, imgNameUncompressed;
        try {
            imgMd5 = SecureUtil.md5(file.getInputStream());
            imgNameCompressed = imgMd5 + "-small";
            imgNameUncompressed = imgMd5;
            // 如果要额外压缩图片
            if (isCompress) {
                File tempFileCompressed = new File(filePath, imgNameCompressed);
                if (!tempFileCompressed.getParentFile().exists()) {//要是父文件夹不存在
                    tempFileCompressed.getParentFile().mkdirs();//创建父级文件路径
                    tempFileCompressed.createNewFile();//再创建文件
                }
                Thumbnails.of(file.getInputStream()).size(200, 200)
                        .outputQuality(0.8f) // 输出图片的质量,scale获得缩略图
                        .outputFormat("jpeg").toFile(tempFileCompressed);
                // 上传压缩的图片/缩略图
                qiniuUtil.uploadFile(tempFileCompressed, imgNameCompressed);
            }
            // 上传原图
            return qiniuUtil.uploadFile(file.getInputStream(), imgNameUncompressed);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String saveAvatar(MultipartFile file, Integer uid, HttpServletRequest request) {
        // 包含返回true,合法的类型
        Assert.isTrue(CONTENT_TYPE.contains(file.getContentType()), "图片类型不合法");
        String url = this.saveImageAndCompress(file, request, true, true);
        userService.update(new UpdateWrapper<User>()
                .eq("id", uid)
                .set("avatar", url));
        return url;
    }

    @Override
    public String saveCover(MultipartFile file, Integer uid, HttpServletRequest request) {
        // 包含返回true,是合法的类型
        Assert.isTrue(CONTENT_TYPE.contains(file.getContentType()), "图片类型不合法");
        String url = this.saveImageAndCompress(file, request, true, false);
        System.out.println(userinfoService.update(new UpdateWrapper<Userinfo>()
                .eq("uid", uid)
                .set("cover_img", url)));
        return url;
    }
}
