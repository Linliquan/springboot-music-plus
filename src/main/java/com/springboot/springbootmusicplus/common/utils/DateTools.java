package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 10:34
 */
public class DateTools {

    public final static String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public final static String SHORT_DATE_FORMAT1 = "yyyy/MM/dd";
    public final static String SHORT_DATE_FORMAT2 = "yyyy年MM月dd日";

    public final static String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";


    /**
     * 获取上月第一天开始时间
     */
    public static Calendar getLastMonthFirstDay(Calendar time) {
        time.add(Calendar.MONTH, -1);
        time.set(Calendar.DATE, 1);
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        return time;
    }

    /**
     * 获取月第一天
     *
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.set(Calendar.DATE, 1);
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        return time.getTime();
    }

    /**
     * 获取当天当天开始时间
     */
    public static int getTodayStartTime() {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        return (int) (time.getTimeInMillis() / 1000L);
    }

    public static Date getDayStartTime(Date date) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        return time.getTime();
    }

    public static Date getDayEndTime(Date date) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.set(Calendar.HOUR_OF_DAY, 23);
        time.set(Calendar.MINUTE, 59);
        time.set(Calendar.SECOND, 59);
        return time.getTime();
    }

    public static Date getDayStartTime() {
        return getDayStartTime(new Date());
    }

    /**
     * 获取上月最后一天结束时间
     */
    public static Calendar getLastMonthLastDay(Calendar time) {
        time.set(Calendar.DATE, 1);
        time.set(Calendar.HOUR_OF_DAY, 23);
        time.set(Calendar.MINUTE, 59);
        time.set(Calendar.SECOND, 59);
        time.add(Calendar.DATE, -1);
        return time;
    }

    /**
     * （yyyy-MM-dd）格式的字符串，转换为Date
     */
    public static Date string2DateShort(String datestr) {
        return string2Date(datestr, SHORT_DATE_FORMAT);
    }

    /**
     * 格式的字符串，转换为Date
     */
    public static Date parse(String datestr, Date defaultValue) {
        if (datestr.startsWith("0")) {
            return defaultValue;
        }
        String[] patternArr = new String[]{
                SHORT_DATE_FORMAT, SHORT_DATE_FORMAT1, LONG_DATE_FORMAT, SHORT_DATE_FORMAT2
        };
        for (String pattern : patternArr) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                return formatter.parse(datestr);
            } catch (Exception e) {
            }
        }
        if (null != defaultValue) {
            return defaultValue;
        }
        throw new RuntimeException("java.text.ParseException: Unparseable date:" + datestr);
    }

    /**
     * ${format}格式的字符串，转换为Date
     */
    public static Date string2Date(String datestr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        datestr = datestr == null ? null : datestr.trim();
        if (datestr == null) {
            throw new IllegalArgumentException("DictParam is null");
        }
        try {
            return formatter.parse(datestr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回日期字符串（yyyy-MM-dd）
     */
    public static String date2ShortString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return formatter.format(date);
    }

    /**
     * 返回时间字符串（yyyy-MM-dd HH:mm:ss）
     */
    public static String date2String(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(LONG_DATE_FORMAT);
        return formatter.format(date);
    }

    public static String date2String(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 返回当前时间的秒数
     */
    public static int unixTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Date fromUnixTime(int seconds) {
        return new Date(seconds * 1000L);
    }

    public static Date fromUnixTime(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 日期(天)转unixtime
     */
    public static int day2Unixtime(String day) {
        return (int) (parse(day, null).getTime() / 1000L);
    }

    /**
     * date转unixtime(秒)
     */
    public static int date2Unixtime(Date date) {
        return (int) (date.getTime() / 1000L);
    }

    public static Date today() {
        return toDay(new Date());
    }

    public static Date toDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 指定日期当天的最后一秒
     */
    public static Date toNight(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 将时间转换成昨天
     */
    public static Date toYesterday(Date date) {
        return add(date, Calendar.DAY_OF_YEAR, -1);
    }

    /**
     * 将时间转换成明天
     */
    public static Date toTommorow(Date date) {
        return add(date, Calendar.DAY_OF_YEAR, 1);
    }

    static Date add(Date date, int field, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(field, value);
        return cal.getTime();
    }

    /**
     * 得到年份
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.YEAR);
    }

    /**
     * 得到月份
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到某天的星期.1~~7
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int rel = 0;
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                rel = 1;
                break;
            case Calendar.TUESDAY:
                rel = 2;
                break;
            case Calendar.WEDNESDAY:
                rel = 3;
                break;
            case Calendar.THURSDAY:
                rel = 4;
                break;
            case Calendar.FRIDAY:
                rel = 5;
                break;
            case Calendar.SATURDAY:
                rel = 6;
                break;
            default:
                rel = 7;
                break;
        }
        return rel;
    }

    public static boolean isToday(Date date) {
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String nowDay = sf.format(now);
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 获取当天的结束时间
     */
    public static Date getTodayEndTime() {
        try {
            return DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
        }
        return null;
    }

    public static Date dateAdd(Date d, int field, int v) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(field,v);
        return cal.getTime();
    }

}
