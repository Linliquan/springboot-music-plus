package com.springboot.springbootmusicplus.common.utils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 14:01
 */
public class WindowQpsControl {


    /**
     * 接受请求窗口
     */
    private final long[] accessWindow;
    /**
     * 窗口大小
     */
    private final int limit;
    /**
     * 指针位置
     */
    private int curPosition;
    /**
     * 时间间隔
     */
    private final long period;

    private final Object lock = new Object();

    /**
     * 1秒内最多400次请求
     *
     * @param limit    限制次数  400
     * @param period   时间间隔  1
     * @param timeUnit 间隔类型  秒
     */
    public WindowQpsControl(int limit, int period, TimeUnit timeUnit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + limit);
        }
        curPosition = 0;
        this.period = timeUnit.toMillis(period);
        this.limit = limit;
        accessWindow = new long[limit];
        Arrays.fill(accessWindow, 0);
    }

    public boolean isPass() {
        long curTime = System.currentTimeMillis();
        synchronized (lock) {
            if (curTime >= period + accessWindow[curPosition]) {
                accessWindow[curPosition++] = curTime;
                curPosition = curPosition % limit;
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());


        WindowQpsControl windowQpsControl = new WindowQpsControl(100, 1, TimeUnit.SECONDS);

        for (int i = 0; i < 110; i++) {
            System.out.println(windowQpsControl.isPass());
        }
        Thread.sleep(2000);
        System.out.println("--------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(windowQpsControl.isPass());
        }
    }


}