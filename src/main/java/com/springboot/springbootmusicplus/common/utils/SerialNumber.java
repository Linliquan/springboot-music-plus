package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 13:52
 */
public class SerialNumber {

    private static final SimpleDateFormat sdft = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 生成业务流水号
     * 系统标识（sysFlg.length位）+时间（14位，年月日时分秒）+系统流水（randomCount位）
     * @param sysFlg      系统标识
     * @param randomCount 随机数位数
     * @return
     */
    public static String createSysSerial(String sysFlg, int randomCount) {
        String date = sdft.format(new Date());
        return sysFlg + date + createSerial(randomCount);
    }

    public static synchronized String createSerial(int randomCount) {
        return RandomStringUtils.randomNumeric(randomCount);
    }

}
