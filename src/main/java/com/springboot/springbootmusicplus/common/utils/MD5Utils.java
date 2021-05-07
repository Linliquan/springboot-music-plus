package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/25 17:12
 */
public class MD5Utils {
    private static final int    SIGNUM      = 1;
    /**
     * hex值
     */
    private static final int    HEX_FLAG    = 16;
    /**
     * 签名的长度
     */
    private static final int    SIGN_LENGTH = 32;
    /**
     * 填充值
     */
    private static final String FILL_CHAR   = "0";
    /**
     * md5 32位加密方法
     *
     * @param input
     * @return
     */
    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(Charset.forName("UTF-8")));
            BigInteger number = new BigInteger(SIGNUM, messageDigest);
            String hashtext = number.toString(HEX_FLAG);
            while (hashtext.length() < SIGN_LENGTH) {
                hashtext = FILL_CHAR + hashtext;
            }
            return hashtext.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        // 方式一: 使用自定义的MD5加密工具类
        String password = MD5Utils.getMd5("abc123ABC");
        System.out.println("password："+ password);

        // 加盐，随机生成6位数字和字母混合的字符串
        String salt = RandomStringUtils.randomAlphanumeric(10);
        System.out.println("salt：" + salt);

        String saltPassword = MD5Utils.getMd5("abc123ABC" + salt);
        System.out.println("saltPassword：" + saltPassword);

        // 方式二: 使用springboot自带的MD5加密封装类DigestUtils
        String pwd = DigestUtils.md5DigestAsHex("abc123ABC".getBytes());
        System.out.println("pwd：" + pwd.toUpperCase());

//        // 方式三：直接引入org.apache.commons.codec.digest.DigestUtils
//        String password2 = DigestUtils.md5Hex("abc123ABC").toUpperCase();
//        System.out.println(password2);

    }
}
