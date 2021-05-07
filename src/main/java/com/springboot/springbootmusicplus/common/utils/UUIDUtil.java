package com.springboot.springbootmusicplus.common.utils;

import java.util.UUID;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 14:00
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println("32位UUID ：" + UUIDUtil.getUUID());
    }
}