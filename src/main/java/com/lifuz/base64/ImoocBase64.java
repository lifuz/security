package com.lifuz.base64;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * 测试Base64 加密方法
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/9/19 17:06
 */
public class ImoocBase64 {

    private static final String src = "imooc security base64";


    /**
     * 原生方法实现base64加解密
     */
    @Test
    public void jdkBase64() {

        BASE64Encoder encoder = new BASE64Encoder();

        String encode = encoder.encode(src.getBytes());

        System.out.println(encode);

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            System.out.println(new String(decoder.decodeBuffer(encode)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * commons-codec 方法实现Base64加解密
     */
    @Test
    public void codesBase64() {

        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());

        System.out.println(new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);


        System.out.println(new String(decodeBytes));


    }

}
