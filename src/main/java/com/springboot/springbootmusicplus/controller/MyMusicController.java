package com.springboot.springbootmusicplus.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootmusicplus.common.enums.FailEnums;
import com.springboot.springbootmusicplus.common.page.PageResponse;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.entity.Musiclink;
import com.springboot.springbootmusicplus.entity.Mymusic;
import com.springboot.springbootmusicplus.service.impl.MusiclinkService;
import com.springboot.springbootmusicplus.service.impl.MymusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/10 16:33
 */
@Slf4j
@RestController
@Api(tags = "我的歌曲信息")
@RequestMapping("/myMusic")
public class MyMusicController {

    @Autowired
    private MymusicService mymusicService;

    @Autowired
    private MusiclinkService musiclinkService;

    @PostMapping("/getMyMusicList")
    @ApiOperation(value = "查询我的收藏歌曲", httpMethod = "POST")
    public Response<List<Mymusic>> getMyMusicList(@RequestParam(required = false) Integer userId) {
        log.info("查询我的收藏歌曲请求：userId：{}", userId);
        List<Mymusic> myMusicList = mymusicService.getMymusicInfoByUserId(userId != null ? userId : 0);
        log.info("查询我的收藏歌曲结果：myMusicList：{}", JSON.toJSONString(myMusicList));
        return Response.succ(myMusicList);
    }

    @PostMapping("/addMusicCollect")
    @ApiOperation(value = "歌曲收藏", httpMethod = "POST")
    public Response<PageResponse<Musiclink>> addMusicCollect(@RequestParam(required = false) int songId,
                                                             @RequestParam(required = false) String songName,
                                                             @RequestParam(required = false) Integer userId) {

        log.info("歌曲收藏请求：userId：{}, songId：{}, songName：{}", userId, songId, songName);
        // 根据用户Id和歌曲名判断歌曲是否重复收藏
        List<Mymusic> myMusicList = mymusicService.getMymusicInfoBySongNameAndUserId(songName, userId);
        if (CollectionUtils.isNotEmpty(myMusicList)) {
            return Response.fail(FailEnums.DATA_DUPLICATION_ERROR.getCode(), "已收藏，请不要重复收藏");
        }
        Musiclink musiclink = musiclinkService.getMusiclinkInfoById(songId);

        // 根据用户Id和歌曲信息，向数据库插入用户收藏的歌曲
        boolean insertMusic = mymusicService.insertMusicInfo(musiclink, userId != null ? userId : 0);
        if (!insertMusic) {
            return Response.fail(FailEnums.DB_OPERATOR_ERROR.getCode(), "歌曲收藏-数据库插入失败！");
        }
        return Response.succ(null, "收藏成功！");
    }

    @PostMapping("/deleteMyMusic")
    @ApiOperation(value = "删除音乐", httpMethod = "POST")
    public Response<PageResponse<Musiclink>> deleteMyMusic(@RequestParam(required = false) Integer myId,
                                                           @RequestParam(required = false) Integer userId) {

        log.info("删除音乐请求：myId：{}, userId：{}", myId, userId);
        boolean deleteMusic = mymusicService.deleteMyMusic(myId, userId != null ? userId : 0);
        if (!deleteMusic) {
            return Response.fail(FailEnums.DB_OPERATOR_ERROR.getCode(), "数据库操作错误！");
        }
        return Response.succ(null, "删除成功！");
    }

}
