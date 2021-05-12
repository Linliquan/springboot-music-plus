package com.springboot.springbootmusicplus.service.impl;

import com.springboot.springbootmusicplus.dao.operator.MymusicOperator;
import com.springboot.springbootmusicplus.entity.Musiclink;
import com.springboot.springbootmusicplus.entity.Mymusic;
import com.springboot.springbootmusicplus.service.IMymusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/12 14:19
 */
@Service
public class MymusicService implements IMymusicService {

    @Autowired
    private MymusicOperator mymusicOperator;

    @Override
    public Mymusic getMymusicInfoById(Integer id) {
        return mymusicOperator.getMymusicInfoById(id);
    }

    public List<Mymusic> getMymusicInfoBySongNameAndUserId(String songName, Integer userId) {
        return mymusicOperator.getMymusicInfoBySongNameAndUserId(songName, userId);
    }

    public List<Mymusic> getMymusicInfoByUserId(Integer userId) {
        return mymusicOperator.getMymusicInfoByUserId(userId);
    }

    public boolean insertMusicInfo(Musiclink musiclink , Integer userId) {
        Mymusic mymusic = new Mymusic();
        mymusic.setMySongname(musiclink.getMlSongname());
        mymusic.setMySinger(musiclink.getMlSinger());
        mymusic.setMySonglink(musiclink.getMlSonglink());
        mymusic.setMyLyriclink(musiclink.getMlLyriclink());
        mymusic.setMyPhotolink(musiclink.getMlPhotolink());
        mymusic.setUserId(userId);
        return mymusicOperator.insertMusicInfo(mymusic);
    }

    public boolean deleteMyMusic(Integer myId, Integer userId) {
        return mymusicOperator.deleteMyMusic(myId, userId);
    }



}
