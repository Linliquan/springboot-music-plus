package com.springboot.springbootmusicplus.controller;

import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.entity.Musiclink;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/getMyMusicList")
    @ApiOperation(value = "查询我的收藏歌曲", httpMethod = "POST")
    public Response<List<Musiclink>> getMyMusicList(@RequestParam(required = false) String songName) {
        return Response.succ(null);
    }

}
