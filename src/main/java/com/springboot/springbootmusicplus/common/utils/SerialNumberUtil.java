package com.springboot.springbootmusicplus.common.utils;

/**
 * @author linliquan
 * @description: 流水号生成工具类SerialNumberUtil
 * @create 2021/5/7 13:53
 */
public class SerialNumberUtil {

    /**
     * 生成订单号
     */
    public static String genOrderNo() {
        return SerialNumber.createSysSerial("QY", 9);
    }

    /**
     * 生成提货码
     * @return
     */
    public static String genPickNo() {
        return SerialNumber.createSerial( 10);
    }
}
