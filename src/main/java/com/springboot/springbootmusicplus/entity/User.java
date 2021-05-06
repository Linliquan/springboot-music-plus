package com.springboot.springbootmusicplus.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6570002271842385911L;

    private Integer userId;

    private String userName;

    private String userPassword;

    private Date createTime;

}