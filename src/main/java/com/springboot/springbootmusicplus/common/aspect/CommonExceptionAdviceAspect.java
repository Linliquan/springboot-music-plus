package com.springboot.springbootmusicplus.common.aspect;

import com.springboot.springbootmusicplus.common.enums.ResponseStatus;
import com.springboot.springbootmusicplus.common.exception.QyException;
import com.springboot.springbootmusicplus.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;
import java.util.List;

/**
 * @author linliquan
 * @description: 使用 controllerAdvice 统一处理拦截controller异常
 * @create 2021/5/27 15:19
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionAdviceAspect {

    /**
     * spring拦截顺序：
     * 请求进来 会按照 filter -> interceptor -> controllerAdvice -> aspect -> controller的顺序调用
     * 当 controller 返回异常 也会按照controller -> aspect -> controllerAdvice -> interceptor -> filter来依次抛出
     */

    @ExceptionHandler(BindException.class)
    public Object handlerBindException(BindException e) {
        log.error("参数绑定错误", e);
        StringBuilder errorMsg = new StringBuilder();
        e.getAllErrors().forEach(x -> errorMsg.append(",").append(x.getDefaultMessage()));
        return Response.fail(ResponseStatus.FAIL.getCode(), errorMsg.toString().substring(1));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return Response.fail(ResponseStatus.FAIL.getCode(), "缺少请求参数:" + e.getParameterName());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Object handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("参数类型错误", e);
        return Response.fail(ResponseStatus.FAIL.getCode(), "参数[" + e.getName() + "]类型错误");
    }

    @ExceptionHandler(ValidationException.class)
    public Object handlerValidationException(ValidationException e) {
        log.error("参数校验错误", e);
        return Response.fail(ResponseStatus.FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数缺失错误 ", e.getMessage());
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        StringBuffer sb = new StringBuffer();
        for (ObjectError objectError : errorList) {
            sb.append(objectError.getDefaultMessage()).append(",");
        }
        return Response.fail(ResponseStatus.FAIL.getCode(), sb.toString());
    }

    /**
     * 自定义异常
     * @param e exception
     * @return
     */
    @ExceptionHandler(QyException.class)
    public Object handlerBusinessException(QyException e) {
        log.error("业务异常：" , e);
        return Response.fail(ResponseStatus.FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e) {
        log.error("系统异常", e);
        return Response.fail(ResponseStatus.FAIL.getCode(), "刚才打了个盹，请稍后再试！");
    }



}
