package com.springboot.springbootmusicplus.common.response;

import com.springboot.springbootmusicplus.common.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 14:46
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -7534840841418774527L;

    @Getter
    @Setter
    private Boolean success;

    @Getter
    @Setter
    private T data;

    /**
     * 默认200成功
     */
    @Getter
    @Setter
    private Integer code = 200;

    @Getter
    @Setter
    private String msg = "操作成功";


    public static <T> Response<T> fail(String msg) {
        Response<T> response = new Response<>();
        response.setSuccess(Boolean.FALSE);
        response.setCode(ResponseStatus.FAIL.getCode());
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> fail(Integer code, String msg) {
        Response<T> response = new Response<>();
        response.setSuccess(Boolean.FALSE);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> fail(Response<T> response, Integer code, String msg) {
        response.setSuccess(Boolean.FALSE);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> degrade(T data, String msg) {
        Response<T> response = new Response<>();
        response.setSuccess(Boolean.TRUE);
        response.setCode(ResponseStatus.DEGRADE.getCode());
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> degrade(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(Boolean.TRUE);
        response.setCode(ResponseStatus.DEGRADE.getCode());
        response.setData(data);
        response.setMsg("服务降级");
        return response;
    }

    public static <T> Response<T> succ(T data, String msg) {
        Response<T> response = new Response<>();
        response.setSuccess(Boolean.TRUE);
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> succ(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(Boolean.TRUE);
        response.setCode(ResponseStatus.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> succ() {
        Response<T> response = new Response<>();
        response.setSuccess(Boolean.TRUE);
        response.setCode(ResponseStatus.SUCCESS.getCode());
        return response;
    }

    @Override
    public String toString() {
        //失败时不打出data
        if (null == success || !success) {
            return "{" +
                    "success=" + success
                    + ", code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
        if (null == data) {
            return null;
        }
        return data.toString();
    }
}
