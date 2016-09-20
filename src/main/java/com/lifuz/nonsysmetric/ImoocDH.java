package com.lifuz.nonsysmetric;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * DH 类型 非对称加解密
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/9/20 11:08
 */
public class ImoocDH {

    private static final String src = "imooc security DH";

    /**
     * jdk 原生方法实现 Dh 加解密
     */
    @Test
    public void jdkDH() throws Exception {

        //1.初始化发送方密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        byte[] senderPublicKeyEnc = keyPair.getPublic().getEncoded();

        //2.初始化接收方密钥
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
        PublicKey recPublicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey) recPublicKey).getParams();

        KeyPairGenerator recKeyPairGenerator = KeyPairGenerator.getInstance("DH");
        recKeyPairGenerator.initialize(dhParameterSpec);

        KeyPair recKeyPair = recKeyPairGenerator.generateKeyPair();
        PrivateKey recPrivateKey = recKeyPair.getPrivate();
        byte[] recPublicKeyEnc = recKeyPair.getPublic().getEncoded();

        //3.密钥构建
        KeyAgreement recKeyAgreement = KeyAgreement.getInstance("DH");
        recKeyAgreement.init(recPrivateKey);
        recKeyAgreement.doPhase(recPublicKey,true);
        SecretKey recSecretKey = recKeyAgreement.generateSecret("DES");

        KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
        x509EncodedKeySpec = new X509EncodedKeySpec(recPublicKeyEnc);
        PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
        KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
        senderKeyAgreement.init(keyPair.getPrivate());
        senderKeyAgreement.doPhase(senderPublicKey,true);

        SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");

        if (Objects.equals(recSecretKey,senderDesKey)){
            System.out.println("双方密钥相同");
        }

        //4.加密
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE,senderDesKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println(Base64.encodeBase64String(result));

        //解密
        cipher.init(Cipher.DECRYPT_MODE,recSecretKey);
        result = cipher.doFinal(result);
        System.out.println(new String(result));

    }

}
