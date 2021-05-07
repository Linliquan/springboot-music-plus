package com.springboot.springbootmusicplus.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/13 16:59
 */
@Getter
@AllArgsConstructor
public enum FailEnums {

    /**
     * 错误枚举
     */
    BIZ_ERROR(100, "业务错误"),
    DATA_DUPLICATION_ERROR(101, "数据重复"),
    PARAM_ERROR(102, "参数错误"),
    NOT_EXISTS_ERROR(103, "没有找到数据"),
    EXISTS_ERROR(104, "数据已存在"),
    DB_OPERATOR_ERROR(105, "数据库操作失败"),
    DATA_ERROR(106, "数据异常"),
    SYSTEM_ERROR(107, "系统异常"),
    UNKNOWN(10001, "未知错误")
    ;

    private final Integer code;

    private final String msg;

    public static FailEnums getCodeEnum(Integer code){
        return Stream.of(values()).filter(b -> b.code.equals(code)).findFirst().orElse(UNKNOWN);
    }
}
