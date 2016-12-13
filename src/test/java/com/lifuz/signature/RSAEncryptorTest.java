package com.lifuz.signature;

import org.junit.Test;

/**
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/12/13 13:54
 */
public class RSAEncryptorTest {

    @Test
    public void test() throws Exception {

        RSAEncryptor rsaEncryptor = RSAEncryptor.getInstance();

        rsaEncryptor.loadKey();

        String text = "123456";

        text = rsaEncryptor.encryptWithBase64(text,1);

        System.out.println(text);

        text = rsaEncryptor.decryptWithBase64(text,2);
        System.out.println(text);

        text = rsaEncryptor.encryptWithBase64(text,2);

        System.out.println(text);

        text = rsaEncryptor.decryptWithBase64(text,1);
        System.out.println(text);

    }

}