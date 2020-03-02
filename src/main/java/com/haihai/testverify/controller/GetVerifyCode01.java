package com.haihai.testverify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class GetVerifyCode01 {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public String getRandom(int lenth) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < lenth; i++) {
            int num = random.nextInt(10);
            code = code + String.valueOf(num);
        }
        return code;
    }

    @ResponseBody
    @RequestMapping("/getVerifyCode")
    public String getVerifyCode(@RequestParam("inputPhoneNumber") String phoneNumber, @RequestParam("inputVerifyCode") String inputVerifyCode) {

        String uid = phoneNumber + ":phoneNumber";//设置存放电话号码的key
        String ucount = phoneNumber + ":count";//设置每个用户的每日获取验证码的次数
        if (!stringRedisTemplate.hasKey(uid)) {//代表不存在这个key
            stringRedisTemplate.opsForValue().set(ucount, "0", 1, TimeUnit.DAYS);
        } else if (Integer.parseInt(stringRedisTemplate.opsForValue().get(ucount)) >= 2) {//代表超过三次
            return "false";
        } else {
            stringRedisTemplate.opsForValue().increment(ucount);//给次数加一
        }

        stringRedisTemplate.opsForValue().set(uid, phoneNumber);
        String verifyCode = this.getRandom(6);
        String uverifyCode = phoneNumber + ":verifyCode";//设置存放验证码的key
        stringRedisTemplate.opsForValue().set(uverifyCode, verifyCode, 120, TimeUnit.SECONDS);
        return "true";
    }
}
