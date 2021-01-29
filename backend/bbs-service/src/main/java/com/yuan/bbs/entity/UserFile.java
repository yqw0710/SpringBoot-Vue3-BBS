package com.yuan.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户上传文件记录表
 * </p>
 *
 * @author yuan
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("y_user_file")
@ApiModel(value = "UserFile对象", description = "用户上传文件记录表")
public class UserFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "上传者的用户id")
    private Integer uid;

    @ApiModelProperty(value = "文件可被访问的url")
    private String url;

    @ApiModelProperty(value = "文件通过计算得出的MD5,防止文件重复保存")
    private String md5;

    @ApiModelProperty(value = "文件后缀名")
    private String fileSuffix;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    @ApiModelProperty(value = "原始文件名字")
    private String originalFileName;

    @ApiModelProperty(value = "修改后的文件名字")
    private String newFileName;

    private LocalDateTime gmtCreate;


}
