package com.springboot.springbootmusicplus.common.exception;


/**
 * @author linliquan
 * @description: 抛出异常，会被捕获，并返回错误信息。
 * @create 2021/4/13 17:22
 */
public class QyException extends RuntimeException {

    public QyException(String message) {
        super(message);
    }

}
