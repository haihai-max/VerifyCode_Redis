package com.haihai.testverify;

import com.haihai.testverify.controller.GetVerifyCode01;
import org.junit.jupiter.api.Test;

public class TestGetRandomCode {

    @Test
    public void getRandomCode(){
        GetVerifyCode01 getVerifyCode=new GetVerifyCode01();
        String code=getVerifyCode.getRandom(6);
        System.out.println(code);
    }
}
