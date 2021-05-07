package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/26 14:49
 */
public class Sha1Utils {

    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String args[]) throws Exception {

        // 方式一：使用自定义sha1加密
        String str = "aa123456";
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + shaEncode(str));

        // 方式二：直接引入org.apache.commons.codec.digest.DigestUtils
        String s = DigestUtils.sha1Hex(str);
        System.out.println("加密：" + s);

    }
}
