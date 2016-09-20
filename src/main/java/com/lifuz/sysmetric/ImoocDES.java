package com.lifuz.sysmetric;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 *
 * DES 和 3DES 对称加解密技术
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/9/20 9:04
 */
public class ImoocDES {

    private static final String SRC = "imooc security DES";

    /**
     * jdk 原生 3DES 加解密
     */
    @Test
    public void jdk3DES() {
        try {

            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//            keyGenerator.init(168);
            keyGenerator.init(new SecureRandom());

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //key转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key key = factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);

            byte[] result = cipher.doFinal(SRC.getBytes());
            System.out.println(Hex.encodeHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,key);
            result = cipher.doFinal(result);
            System.out.println(new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * jdk 原生 Des 加解密
     */
    @Test
    public void jdkDES(){
        try {

            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key key = factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);

            byte[] result = cipher.doFinal(SRC.getBytes());
            System.out.println(Hex.encodeHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,key);
            result = cipher.doFinal(result);
            System.out.println(new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
