package com.springboot.springbootmusicplus.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/8 15:40
 */
@Data
@ApiModel("用户注册请求")
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = -6489615815743188295L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户密码")
    private String userPassword;

}
