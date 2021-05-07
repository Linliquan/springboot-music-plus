package com.springboot.springbootmusicplus.common.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 10:33
 */
public class DateUtils extends DateTools {

    public static int compareDate(Date DATE1, Date DATE2) {
        FastDateFormat formatter = FastDateFormat.getInstance(SHORT_DATE_FORMAT);
        try {
            Date dt1 = formatter.parse(date2ShortString(DATE1));
            Date dt2 = formatter.parse(date2ShortString(DATE2));
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int compareLongDate(Date DATE1, Date DATE2) {
        FastDateFormat formatter = FastDateFormat.getInstance(LONG_DATE_FORMAT);
        try {
            Date dt1 = formatter.parse(date2String(DATE1));
            Date dt2 = formatter.parse(date2String(DATE2));
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static Boolean compareDateEqual(Date DATE1, Date DATE2) {
        FastDateFormat formatter = FastDateFormat.getInstance(SHORT_DATE_FORMAT);
        try {
            Date dt1 = formatter.parse(date2ShortString(DATE1));
            Date dt2 = formatter.parse(date2ShortString(DATE2));
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static Date longString2longDate(String str){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
