package com.lifuz.remark;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sha 类型 摘要加密
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/9/19 20:46
 */
public class ImmocSha {

    private static final String src = "imooc security sha";



    /**
     * jdk 的 sha1 摘要加密
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void jdkSha1() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");

        md.update(src.getBytes());

        System.out.println(Hex.encodeHexString(md.digest()));
    }

    /**
     * commons-codec 的 sha1 摘要加密
     */
    @Test
    public void ccSha1() {
        String sha = DigestUtils.sha1Hex(src);

        System.out.println(sha);
    }

}
