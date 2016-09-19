package com.lifuz.remark;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * md 摘要加密
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/9/19 20:06
 */
public class ImoocMD {

    private static final String src = "imooc security md";

    /**
     * 测试 jdk md5摘要加密
     */
    @Test
    public void jdkMD5(){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] md5Bytes = md.digest(src.getBytes());
            System.out.println(Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    /**
     * commons-codec 方法实现 md5 加密
     */
    @Test
    public void ccMD5() {
        String encode = DigestUtils.md5Hex(src);

        System.out.println(encode);
    }

}
