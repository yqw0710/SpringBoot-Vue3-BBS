package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bbs.common.pojo.UserDigestDto;
import com.yuan.bbs.entity.Userinfo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-16
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {
/*
    @Update("UPDATE y_userinfo SET #{key} = #{value} WHERE uid = #{uid} ")
    void updateOneInfo(@Param("key") String key, @Param("value") String value, @Param("uid") Integer uid);*/

    @Select("select  i.uid,u.nickname,u.avatar,i.sign,i.sex,i.cover_img from y_user u,y_userinfo i where u.id=#{uid} and i.uid=u.id")
    UserDigestDto getDigest(Integer uid);
}
