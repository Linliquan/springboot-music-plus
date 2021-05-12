package com.springboot.springbootmusicplus.dao.operator;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springboot.springbootmusicplus.dao.repository.MymusicMapper;
import com.springboot.springbootmusicplus.entity.Mymusic;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/6 17:05
 */
@Service
public class MymusicOperator extends BaseOperator<MymusicMapper, Mymusic> {

    @Autowired
    private MymusicMapper mymusicMapper;

    /**
     * 根据 id 查询我的收藏音乐信息
     * @param id
     * @return
     */
    public Mymusic getMymusicInfoById(Integer id) {
        return mymusicMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据歌曲名和用户id查询歌曲信息
     * @param songName
     * @param userId
     * @return
     */
    public List<Mymusic> getMymusicInfoBySongNameAndUserId(String songName, Integer userId) {
        LambdaQueryWrapper<Mymusic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Mymusic::getMySongname, songName);
        wrapper.eq(Mymusic::getUserId, userId);
        List<Mymusic> list = mymusicMapper.selectList(wrapper);
        return CollectionUtils.isNotEmpty(list) ? list : null;
    }

    /**
     * 根据 userId 查询收藏的歌曲
     * @param userId
     * @return
     */
    public List<Mymusic> getMymusicInfoByUserId(Integer userId) {
        LambdaQueryWrapper<Mymusic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Mymusic::getUserId, userId);
        List<Mymusic> list = mymusicMapper.selectList(wrapper);
        return CollectionUtils.isNotEmpty(list) ? list : null;
    }

    /**
     * 插入收藏的歌曲
     * @param mymusic
     * @return
     */
    public boolean insertMusicInfo(Mymusic mymusic) {
        return mymusicMapper.insertSelective(mymusic) > 0;
    }

    /**
     * 删除音乐
     * @param myId
     * @param userId
     * @return
     */
    public boolean deleteMyMusic(Integer myId, Integer userId) {
        LambdaQueryWrapper<Mymusic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Mymusic::getMyId, myId);
        wrapper.eq(Mymusic::getUserId, userId);
        return mymusicMapper.delete(wrapper) > 0;
    }

}
