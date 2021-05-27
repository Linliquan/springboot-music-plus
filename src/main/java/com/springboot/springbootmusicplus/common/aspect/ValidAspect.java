package com.springboot.springbootmusicplus.common.aspect;

import com.springboot.springbootmusicplus.common.exception.QyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author linliquan
 * @description: dubbo参数校验切面拦截
 * @create 2021/4/13 17:20
 */
@Aspect
@Component
@Slf4j
public class ValidAspect {
    private static Validator validator;

    static {
        validator = Validation.byDefaultProvider().configure().buildValidatorFactory().getValidator();
    }


    @Pointcut("@annotation(org.springframework.validation.annotation.Validated))")
    private void validateMethod() {
    }

    @Before("validateMethod()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 1 && args[0] == null) {
            throw new QyException("请输入正确的参数!");
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 执行方法参数的校验
        Set<ConstraintViolation<Object>> constraintViolations = validator.forExecutables().validateParameters(joinPoint.getThis(), signature.getMethod(), args);
        StringBuffer messages = new StringBuffer();
        for (ConstraintViolation<Object> error : constraintViolations) {
            messages.append(error.getMessage()).append(",");
        }
        if (StringUtils.isNotBlank(messages.toString())) {
            throw new QyException(messages.substring(0, messages.length() - 1));
        }
    }


}
