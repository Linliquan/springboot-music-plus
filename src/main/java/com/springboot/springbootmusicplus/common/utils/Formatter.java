package com.springboot.springbootmusicplus.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/12 17:06
 */
public class Formatter {

    public static BigDecimal long2BigDecimal(Long l) {
        if (Objects.nonNull(l)) {
            BigDecimal bigDecimal = BigDecimal.valueOf(l.doubleValue() / 100);
            return bigDecimal.setScale(2, RoundingMode.HALF_UP);
        } else {
            return null;
        }
    }

    public static Long bigDecimal2Long(BigDecimal b) {
        if (Objects.nonNull(b)) {
            String value = String.format("%.2f", Double.parseDouble(b.setScale(2, RoundingMode.HALF_UP).toString()) * 100);
            return Long.parseLong(value.substring(0, value.length() - 3));

            // 下面会丢失精度  18.90 会变为 1889
//            return new Double(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100).longValue();
        } else {
            return 0L;
        }
    }

    public static String bigDecimal2String(BigDecimal b) {
        if (b == null || BigDecimal.ZERO.compareTo(b) == 0) {
            return "0";
        } else {
            return b.stripTrailingZeros().toPlainString();
        }
    }

    public static Integer bigDecimal2Int(BigDecimal b) {
        if (Objects.nonNull(b)) {
            String value = String.format("%.2f", Double.parseDouble(b.setScale(0, RoundingMode.HALF_UP).toString()));
            return Integer.parseInt(value.substring(0, value.length() - 3));
        } else {
            return 0;
        }
    }

    public static Date localDateTime2Date(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Long bigDecimalHalfUpLong(BigDecimal b) {
        if (Objects.nonNull(b)) {
            BigDecimal decimal = b.setScale(2, RoundingMode.HALF_UP);
            return new Double(decimal.doubleValue() * 100).longValue();
        } else {
            return 0L;
        }
    }

    public static void main(String[] args) {

        BigDecimal bigDecimal1 = new BigDecimal(18.901);
        Long aLong = bigDecimal2Long(bigDecimal1);
        System.out.println(aLong);

        BigDecimal bigDecimal2 = new BigDecimal(100.901);
        String b2 = bigDecimal2String(bigDecimal2);
        System.out.println(b2);

        System.out.println("------------------------");
        double v3 = Double.parseDouble(bigDecimal1.toString()) * 100;
        System.out.println(v3);
        String value = String.format("%.2f", Double.parseDouble(bigDecimal1.toString()) * 100);
        System.out.println(value);
        long l2 = Long.parseLong(value.substring(0, value.length() - 3));
        System.out.println(l2);

        System.out.println("------------------------");
        BigDecimal bigDecimal3 = new BigDecimal(102.56);
        Integer a = bigDecimal2Int(bigDecimal3);
        System.out.println(a);

    }
}
