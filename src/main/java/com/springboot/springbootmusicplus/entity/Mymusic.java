package com.springboot.springbootmusicplus.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Mymusic implements Serializable {
    private static final long serialVersionUID = 8008141463914010237L;

    private Integer myId;

    private String mySongname;

    private String mySinger;

    private String mySonglink;

    private String myLyriclink;

    private String myPhotolink;

    private Integer userId;
}