package com.haihai.testverify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerifyCode {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @RequestMapping("/verifyCode")
    public String verifyCode(@RequestParam("inputPhoneNumber") String phoneNumber, @RequestParam("inputVerifyCode") String inputVerifyCode){

        String uverifyCode=phoneNumber+":verifyCode";
        String key=stringRedisTemplate.opsForValue().get(uverifyCode);
        if (inputVerifyCode.equals(key)){
            return "true";
        }
        return "false";
    }
}
