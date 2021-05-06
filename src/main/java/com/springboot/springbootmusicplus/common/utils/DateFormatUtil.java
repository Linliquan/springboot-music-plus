package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/12 19:47
 */
public class DateFormatUtil {

    public static final FastDateFormat HH_MM_SS = FastDateFormat.getInstance("HH:mm:ss");
    public static final FastDateFormat YYYY_MM_DD_HH_MM_SS = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat YYYY_MM_DD = FastDateFormat.getInstance("yyyy-MM-dd");

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_FORMAT_CHINESE = "yyyy年M月d日";
    public static String TIME_FORMAT = "yyyyMMddHHmmss";


    public static Date convertDateByString(FastDateFormat fastDateFormat, String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        try {
            return fastDateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convertStringByDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, TIME_FORMAT);
    }

    public static void main(String[] args) {
        String date = "2021-04-12 12:20:01";
        System.out.println(convertDateByString(YYYY_MM_DD_HH_MM_SS, date));

        System.out.println(convertStringByDateTime(new Date()));
    }
}
