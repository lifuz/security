package com.lifuz.sysmetric;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 *
 * AES 对称加解密技术
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/9/20 9:33
 */
public class ImoocAES {

    public static final String SRC = "imooc security aes";

    /**
     * jdk 原生 Aes 加解密
     */
    @Test
    public void jdkAES() throws Exception {

        //生产Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        //key转换
        Key key = new SecretKeySpec(keyBytes,"AES");

        //加密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] result = cipher.doFinal(SRC.getBytes());
        System.out.println(Base64.encodeBase64String(result));

        //解密
        cipher.init(Cipher.DECRYPT_MODE,key);
        result = cipher.doFinal(result);
        System.out.println(new String(result));


    }

}
