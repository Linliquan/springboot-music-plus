package com.springboot.springbootmusicplus.controller;

import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.model.request.SongRearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * @author linliquan
 * @description:
 * @create 2021/5/8 14:37
 */
@Slf4j
@RestController
@Api(tags = "歌曲信息")
@RequestMapping("/musicLink")
public class MusicLinkController {


    @PostMapping("/getSongRearch")
    @ApiOperation(value = "歌曲搜索功能", httpMethod = "POST")
    public Response<Boolean> getSongRearch(@RequestBody SongRearchRequest request) {

        return Response.succ(true);
    }

}
