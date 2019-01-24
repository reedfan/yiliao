package com.reed.ustc.utils;

import com.reed.ustc.config.RedisConfig;
import com.reed.ustc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * created by reedfan on 2019/1/24 0024
 */
@RestController
public class RedisTest {
    @Autowired
    private RedisConfig redisConfig;
    @GetMapping("/redistest")
    public Integer test() {

      Integer result = (Integer) redisConfig.get("LOGIN_TiCKET933a63c348754713aa86b44addf6d5b0");

      return result;





    }
}
