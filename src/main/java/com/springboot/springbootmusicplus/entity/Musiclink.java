package com.springboot.springbootmusicplus.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Musiclink implements Serializable {
    private static final long serialVersionUID = -5149909946147476012L;

    private Integer mlId;

    private String mlSongname;

    private String mlSinger;

    private String mlSonglink;

    private String mlLyriclink;

    private String mlPhotolink;
}