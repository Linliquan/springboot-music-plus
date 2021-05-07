package com.springboot.springbootmusicplus.common.utils;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 14:10
 */
public class LongUtils {

    /**
     * 如果数字为空或者为0，则返回 true, 否则返回 false
     *
     * @param number 长整型数字
     * @return 是否为空
     */
    public static boolean isBlank(Long number) {
        return number == null || number == 0L;
    }

    /**
     * 如果数字不为空且数字不为 0，则返回 true, 否则返回 false
     *
     * @param number 长整型数字
     * @return 是否不为空
     */
    public static boolean isNotBlank(Long number) {
        return !isBlank(number);
    }

    /**
     * 判断数字是否为空，为空返回为 true，否则返回为 false
     */
    public static boolean isEmpty(Long number) {
        return number == null;
    }

    /**
     * 判断数字是否不为空，为空返回为 false，否则返回 true
     */
    public static boolean isNotEmpty(Long number) {
        return !isEmpty(number);
    }

}
