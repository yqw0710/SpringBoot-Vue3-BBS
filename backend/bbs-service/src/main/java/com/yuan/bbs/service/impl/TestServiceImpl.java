package com.yuan.bbs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.entity.Test;
import com.yuan.bbs.mapper.TestMapper;
import com.yuan.bbs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试用表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2021-01-17
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void RedisTest01() {
        redisTemplate.opsForValue().set("key0x00", "val0x00");
        redisTemplate.opsForValue().setBit("key0x01", 2, true);
        redisTemplate.opsForValue().setBit("key0x01", 3, true);
        redisTemplate.opsForValue().set("key0x02", Result.succ());
    }

    @Override
    public void RedisTest02() {
        System.out.println(redisTemplate.opsForValue().get("key0x00"));
        System.out.format("[%s %s %s]\n",
                redisTemplate.opsForValue().getBit("key0x01", 2),
                redisTemplate.opsForValue().getBit("key0x01", 3),
                redisTemplate.opsForValue().getBit("key0x01", 4));
        Long bitCount = redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount("key0x01".getBytes()));
        System.out.println("bit count" + bitCount);
    }
}
