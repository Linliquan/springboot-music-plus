package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/26 11:36
 */
public class AESUtil {

    private static final String encoding = "UTF-8";
    public static String encryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] byteContent = content.getBytes(encoding);
        // 注意，为了能与 iOS 统一
        // 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
        byte[] enCodeFormat = key.getBytes(encoding);
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(byteContent);
        // 同样对加密后数据进行 base64 编码
        String base64 = new Base64().encodeToString(encryptedBytes);
        return base64;
        //进行url编码 去掉= ? &
        //return URLEncoder.encode(base64,encoding);

    }
    public static String decryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, IOException {
        //URL解码
//        content = URLDecoder.decode(content,encoding);
        // base64 解码
        byte[] encryptedBytes = Base64.decodeBase64(content);
        byte[] enCodeFormat = key.getBytes(encoding);
        SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] result = cipher.doFinal(encryptedBytes);
        return new String(result, encoding);
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encryptAES("aa123456", "ILoveChina123456");
        // 输出：0DItsKvJPkKWDgRxS59nhg==
        System.out.println("加密后：" + encrypt);

        String decrypt = decryptAES(encrypt , "ILoveChina123456");
        System.out.println("解密后：" + decrypt);
    }
}