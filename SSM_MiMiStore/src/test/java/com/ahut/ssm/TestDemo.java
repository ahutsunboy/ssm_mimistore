package com.ahut.ssm;

import com.ahut.utils.MD5Util;
import org.junit.Test;

public class TestDemo {

    @Test
    public void getMD5Num(){
        String md5 = MD5Util.getMD5("000000");
        System.out.println(md5);
    }
}
