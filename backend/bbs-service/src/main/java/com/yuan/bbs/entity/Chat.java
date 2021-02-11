package com.yuan.bbs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;

/**
 * <p>
 * 聊天记录表
 * </p>
 *
 * @author yuan
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("y_chat")
@ApiModel(value = "Chat对象", description = "聊天记录表")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "发送者id(send_id)")
    private Integer sid;

    @ApiModelProperty(value = "接收者id(receive_id)")
    private Integer rid;

    @ApiModelProperty(value = "消息的内容")
    private String content;

    @ApiModelProperty(value = "是否已读,默认0未读")
    private Boolean read;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created;


    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private String nickname;


    public void setSidFromPrincipal(Principal principal) {
        this.sid=Integer.parseInt(principal.getName());
    }
    public String  getReceive(){
      return  this.rid+"";
    }

}
