package com.springboot.springbootmusicplus.common.utils;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 14:10
 */
public class IntegerUtils {

    private IntegerUtils() {
    }

    /**
     * 如果数字为空或者小于等于0，则返回 true, 否则返回 false
     *
     * @param number 整形数字
     * @return 是否为空
     */
    public static boolean isBlank(Integer number) {
        return number == null || number <= 0;
    }

    /**
     * 如果数字不为空且数字不为 0，则返回 true, 否则返回 false
     *
     * @param number 长整型数字
     * @return 是否大于0
     */
    public static boolean isNotBlank(Integer number) {
        return !isBlank(number);
    }

    /**
     * 判断数字是否为空，为空返回为 true，否则返回为 false
     */
    public static boolean isEmpty(Integer number) {
        return number == null;
    }

    /**
     * 判断数字是否不为空，为空返回为 false，否则返回 true
     */
    public static boolean isNotEmpty(Integer number) {
        return !isEmpty(number);
    }
}
