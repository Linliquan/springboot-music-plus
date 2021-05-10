package com.springboot.springbootmusicplus.dao.operator;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springboot.springbootmusicplus.dao.repository.MusiclinkMapper;
import com.springboot.springbootmusicplus.entity.Musiclink;
import com.springboot.springbootmusicplus.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/6 17:07
 */
@Service
public class MusiclinkOperator extends BaseOperator<MusiclinkMapper, Musiclink> {

    @Resource
    private MusiclinkMapper musiclinkMapper;

    /**
     * 根据 id 查询歌曲信息
     * @param id
     * @return
     */
    public Musiclink getMusiclinkInfoById(Integer id) {
        return musiclinkMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据歌曲名查询歌曲信息-模糊搜索
     * @param songName
     * @return
     */
    public List<Musiclink> getMusiclinkInfoBySongName(String songName) {
        LambdaQueryWrapper<Musiclink> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(songName), Musiclink::getMlSongname, songName);
        List<Musiclink> musiclinkList = musiclinkMapper.selectList(wrapper);
        return CollectionUtils.isNotEmpty(musiclinkList) ? musiclinkList : null;
    }

}
