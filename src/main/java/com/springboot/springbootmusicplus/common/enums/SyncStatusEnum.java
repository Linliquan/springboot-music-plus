package com.springboot.springbootmusicplus.common.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author linliquan
 * @description: 同步状态枚举
 * @create 2021/3/23 13:40
 */
public enum SyncStatusEnum {

    /**
     * 同步状态枚举：同步状态：0-同步失败，1-同步失败
     */
    SYNC_FAIL(0,"同步失败"),
    SYNC_SUCCESS(1,"同步成功"),
    UNKNOWN( 100, "未知")
    ;

    @Getter
    private final Integer code;

    @Getter
    private final String name;

    SyncStatusEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public static SyncStatusEnum getCodeEnum(Integer code){
        return Stream.of(values()).filter(b -> b.code.equals(code)).findFirst().orElse(UNKNOWN);
    }
}
