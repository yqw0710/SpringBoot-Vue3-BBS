package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.entity.Log;
import com.yuan.bbs.mapper.LogMapper;
import com.yuan.bbs.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作记录表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2020-07-16
 */


@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
