package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.entity.Sign;
import com.yuan.bbs.mapper.SignMapper;
import com.yuan.bbs.service.SignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 用户签到表 服务实现类 写的太复杂!
 * </p>
 * 还可以加的补签功能,bitField set
 *
 * @author yuan
 * @since 2020-07-26
 */
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements SignService {


    @Resource
    SignMapper mapper;


}
