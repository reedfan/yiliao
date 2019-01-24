package com.reed.ustc.service.impl;

import com.reed.ustc.config.RedisConfig;
import com.reed.ustc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * created by reedfan on 2019/1/24 0024
 */
@Service
public class RedisServiceImpl implements RedisService {
//    @Autowired
//    private StringRedisTemplate template;
//    @Override
//    public void setKey(String key, String value) {
//        ValueOperations<String, String> ops = template.opsForValue();
//        ops.set(key,value);
//
//    }
//
//    @Override
//    public void setKeyVsExpireTime(String key, String value, long time) {
//        ValueOperations<String, String> ops = template.opsForValue();
//        ops.set(key, value,time,TimeUnit.SECONDS);
//    }
//
//    @Override
//    public String getValue(String key) {
//        ValueOperations<String, String> ops = this.template.opsForValue();
//        return ops.get(key);
//    }








}
