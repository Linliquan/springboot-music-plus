package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/26 11:30
 */
public class AESUtils {
    private static Logger logger = LogManager.getLogger(AESUtils.class);

    private static final String ENCODEING = "UTF-8";
    private static final String ALGORITHM = "AES";//加密算法

    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//算法/工作模式/填充方式

    /**
     * 加密
     *
     * @param plaintext 明文
     * @param secureKey 16位长度的密钥
     * @return
     */
    public static String encrypt(String plaintext, String secureKey) throws Exception {
        SecretKeySpec sks = getSecretKeySpec(secureKey);
        Cipher encryptCipher = getCipher(Cipher.ENCRYPT_MODE, sks);
        byte[] result = encryptCipher.doFinal(plaintext.getBytes(ENCODEING));
        return Base64.encodeBase64String(result);
    }

    /**
     * 加密
     *
     * @param bytes
     * @param secureKey 16位长度的密钥
     * @return
     */
    public static String encryptBytes(byte[] bytes, String secureKey) throws Exception {
        SecretKeySpec sks = getSecretKeySpec(secureKey);
        Cipher encryptCipher = getCipher(Cipher.ENCRYPT_MODE, sks);
        byte[] result = encryptCipher.doFinal(bytes);
        return Base64.encodeBase64String(result);
    }

    /**
     * 解密
     *
     * @param ciphertext 密文
     * @return secureKey 16位长度的密钥
     * @throws Exception
     */
    public static String decrypt(String ciphertext, String secureKey) throws Exception {
        SecretKeySpec sks = getSecretKeySpec(secureKey);
        Cipher decryptCiphe = getCipher(Cipher.DECRYPT_MODE, sks);//initDecryptCipher(secureKey);
        byte[] result = decryptCiphe.doFinal(Base64.decodeBase64(ciphertext));
        return new String(result, ENCODEING);
    }

    private static Cipher getCipher(int cipherMode, SecretKeySpec sks) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(cipherMode, sks);
        return cipher;
    }

    private static SecretKeySpec getSecretKeySpec(String secureKey) throws Exception {
        if (secureKey == null || secureKey.trim().equals("") || secureKey.length() != 16) {
            throw new Exception("密钥不能为空或密钥长度不对");
        }
        byte[] raw = secureKey.getBytes(ENCODEING);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        return skeySpec;
    }

    /**
     * @return
     * @Comment 加密不限制密码长度
     * @Author Ron
     * @Date 2017年9月12日 下午3:21:59
     */
    public static String encryptNotLimit(String plaintext, String secureKey) throws Exception {
        SecretKeySpec sks = getSecretKeySpecByRandomSeed(secureKey);
        Cipher encryptCipher = getCipher(Cipher.ENCRYPT_MODE, sks);
        byte[] result = encryptCipher.doFinal(plaintext.getBytes(ENCODEING));
        return Hex.encodeHexString(result);
    }

    /**
     * @return
     * @Comment 解密不限制密码长度
     * @Author Ron
     * @Date 2017年9月12日 下午3:22:30
     */
    public static String decryptNotLimit(String ciphertext, String secureKey) throws Exception {
        SecretKeySpec sks = getSecretKeySpecByRandomSeed(secureKey);
        Cipher decryptCiphe = getCipher(Cipher.DECRYPT_MODE, sks);
        byte[] result = decryptCiphe.doFinal(Hex.decodeHex(ciphertext.toCharArray()));
        return new String(result, ENCODEING);
    }

    /**
     * @return
     * @Comment 构造私钥
     * @Author Ron
     * @Date 2017年9月12日 下午3:22:59
     */
    private static SecretKeySpec getSecretKeySpecByRandomSeed(String secureKey) {
        SecretKeySpec sks = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            //安全随机数生成器
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");//使用默认的SHA1PRNG算法
            secureRandom.setSeed(secureKey.getBytes(ENCODEING));
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] secretKeyEncoded = secretKey.getEncoded();
            sks = new SecretKeySpec(secretKeyEncoded, ALGORITHM);
        } catch (Exception e) {
            logger.error("", e);
        }
        return sks;
    }

    public static void main(String[] args) throws Exception {
        String key = "ILoveChina123456";
        String src = "aa123456";
        String enString = encrypt(src, key);
        // 输出：0DItsKvJPkKWDgRxS59nhg==
        System.out.println("加密后的数据：" + enString);
        System.out.println("解密后的数据：" + decrypt(enString, key));

        //不限制密钥长度
        String nkey = "ILoveChina_ILoveChina";
        enString = encryptNotLimit(src, nkey);
        System.out.println("加密后的数据：" + enString);
        System.out.println("解密后的数据：" + decryptNotLimit(enString, nkey));
    }
}