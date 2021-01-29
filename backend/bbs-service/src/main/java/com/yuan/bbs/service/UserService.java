package com.yuan.bbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.bbs.common.pojo.LoginDto;
import com.yuan.bbs.common.pojo.RegisterDto;
import com.yuan.bbs.common.pojo.UserDto;
import com.yuan.bbs.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yuan
 * @since 2020-07-08
 */
public interface UserService extends IService<User>, UserDetailsService {

    /**
     * 根据uid获取头像,并可以加入缓存
     *
     * @param uid 用户id
     * @return 用户头像url
     */
    String getAvatarByUid(int uid);


    UserDto getUserNewestData(Integer uid);

    boolean updateNickname(Integer uid, String newVal);

    UserDto login(LoginDto dto);

    UserDto register(RegisterDto dto);

}
