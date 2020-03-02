package com.haihai.testverify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest(classes = TestVerifyApplication.class)
public class TestJedis {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testJedis(){
        stringRedisTemplate.opsForValue().set("haihai","21");
        //String haihai = stringRedisTemplate.opsForValue().get("haihai");
        //System.out.println(haihai);

    }
}
