package com.yuan.bbs.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.pojo.LoginDto;
import com.yuan.bbs.common.pojo.RegisterDto;
import com.yuan.bbs.common.pojo.UserDto;
import com.yuan.bbs.common.utils.CommonUtil;
import com.yuan.bbs.common.utils.JwtUtil;
import com.yuan.bbs.entity.Sign;
import com.yuan.bbs.entity.User;
import com.yuan.bbs.entity.Userinfo;
import com.yuan.bbs.mapper.UserMapper;
import com.yuan.bbs.service.SignService;
import com.yuan.bbs.service.UserService;
import com.yuan.bbs.service.UserinfoService;
import com.yuan.bbs.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2020-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    UserMapper userMapper;
    @Autowired
    JwtUtil jwtUtils;
    @Autowired
    UserinfoService userinfoService;
    @Autowired
    SignService signService;


    /**
     * 根据uid获取头像
     *
     * @param uid 用户id
     * @return 用户头像url
     */
    @Override
    public String getAvatarByUid(int uid) {
        return userMapper.getAvatarId(uid);
    }

    @Override
    public UserDto login(LoginDto dto) {
        User user = userMapper.findUserByUsername(dto.getUsername());
        if (user != null) {
            String EncryptedPassword = CommonUtil.md5(dto.getPassword() + user.getSalt());
            if (user.getPassword().equals(EncryptedPassword)) {
                SecurityUtil.setUserToSecurity(user);
                String token = jwtUtils.generateToken(user.getId());
                return new UserDto().setToken(token)
                        .setNickname(user.getNickname())
                        .setUsername(user.getUsername())
                        .setAvatar(user.getAvatar())
                        .setUid(user.getId());
            }
        }
        return null;
    }

    @Override
    public UserDto register(RegisterDto dto) {
        User user = new User();
        String slat = "jojo";  //用随机盐值更好
        user.setPassword(CommonUtil.md5(dto.getPassword() + slat))
                .setUsername(dto.getUsername()).setSalt(slat)
                .setNickname(dto.getUsername());// 默认昵称为用户名
        // 保存对象同时，mybatis也会把自动填充主键
        // 校验dto避免数据重复(ps:前端页面有判断存在了，但直接访问接口的方式有错也可以不管)
        this.save(user);
        System.out.println("user>register:");
        System.out.println(user);
        //获取uid 生成jwt,并保存生成对应的用户信息和签到信息
        Integer uid = user.getId();
        String username = user.getUsername();
        userinfoService.save(new Userinfo().setUid(uid));
        signService.save(new Sign().setUid(uid));
        String token = jwtUtils.generateToken(uid);
        return new UserDto().setToken(token).setUid(uid).setNickname(username).setUsername(username);
    }

    @Override
    public UserDto getUserNewestData(Integer uid) {
        return userMapper.getNewest(uid);
    }

    @Override
    public boolean updateNickname(Integer uid, String newVal) {
        if (StringUtils.hasText(newVal) && newVal.length() < 20) {
            return this.update(new UpdateWrapper<User>()
                    .set("nickname", newVal).eq("id", uid));
        } else {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findById(username);//这里传进来的是解析token获得的uid
        if (user == null) {  //如果数据库里没有用户名，则认证失败
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
