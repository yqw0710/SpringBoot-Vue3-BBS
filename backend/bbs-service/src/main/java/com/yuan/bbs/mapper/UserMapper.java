package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bbs.common.pojo.UserDto;
import com.yuan.bbs.entity.User;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-08
 */
public interface UserMapper extends BaseMapper<User> {

    @ResultType(String.class)
    @Select("select nickname from y_user where id=#{uid}")
    String getNicknameById(Integer uid);

    @ResultType(String.class)
    @Select("select avatar from y_user where id=#{uid}")
    String getAvatarId(Integer uid);

    @ResultType(UserDto.class)
    @Select("select id as uid ,username,avatar,nickname,point from y_user where id=#{uid}")
    UserDto getNewest(Integer uid);

    @Select("select * from y_user where id =#{id}")
    User findById(String id);

    @Select("select * from y_user where username =#{username}")
    User findUserByUsername(String username);
}
