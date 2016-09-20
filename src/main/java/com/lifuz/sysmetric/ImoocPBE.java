package com.lifuz.sysmetric;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * PBE 对称加解密
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/9/20 9:47
 */
public class ImoocPBE {

    public static final String SRC = "imooc security pbe";

    /**
     * jdk 原生 pbe 加解密
     * @throws Exception
     */
    @Test
    public void jdkPBE() throws Exception {

        //初始化 salt
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(8);

        //口令和秘钥
        String passwd = "imooc";

        PBEKeySpec pbeKeySpec = new PBEKeySpec(passwd.toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");

        Key key = factory.generateSecret(pbeKeySpec);

        //加密
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100);
        Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
        cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
        byte[] result = cipher.doFinal(SRC.getBytes());
        System.out.println(Base64.encodeBase64String(result));

        //解密
        cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
        result = cipher.doFinal(result);
        System.out.println(new String(result));

    }


}
