package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.bbs.common.pojo.SignInfoDto;
import com.yuan.bbs.entity.Sign;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 用户每月签到表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-07-26
 */
public interface SignMapper extends BaseMapper<Sign> {

    /**
     * @return 获取网站总签到次数前十的用户
     */
    @Select("select u.id as uid,u.avatar,u.nickname,s.sign_cont_all as times " +
            "from y_user u,y_sign s where s.uid=u.id and s.sign_cont_all!=0 " +
            "order by s.sign_cont_all DESC limit 10")
    List<SignInfoDto> getAllSignRank();

    @Select("select u.id as uid,u.avatar,u.nickname,s.sign_cont_now as times " +
            "from y_user u,y_sign s where s.uid=u.id " +
            "order by s.sign_cont_now DESC limit 10;")
    List<SignInfoDto> getNowSignRank();

    @Update("update y_sign set sign_record=concat(sign_record,#{str}) where uid =#{uid} ")
    void updateSignRecordWithConcat(@Param("str") String str, @Param("uid") Integer uid);
}
