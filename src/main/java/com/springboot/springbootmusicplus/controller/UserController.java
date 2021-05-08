package com.springboot.springbootmusicplus.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootmusicplus.common.enums.FailEnums;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.entity.User;
import com.springboot.springbootmusicplus.model.request.SongRearchRequest;
import com.springboot.springbootmusicplus.model.request.UserRegisterRequest;
import com.springboot.springbootmusicplus.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/30 15:55
 */
@Slf4j
@RestController
@Api(tags = "用户信息")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userRegister")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    public Response<Boolean> userRegister(@RequestBody UserRegisterRequest request) {

        List<User> userList = userService.getUserInfoByUserName(request.getUserName());
        if (CollectionUtils.isNotEmpty(userList)) {
            return Response.fail(FailEnums.EXISTS_ERROR.getCode(), "用户名已存在！");
        }
        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserPassword(request.getUserPassword());
        boolean result = userService.insertUser(user);
        if (!result) {
            return Response.fail(FailEnums.DATA_ERROR.getCode(), "插入用户失败！");
        }
        return Response.succ(null, "用户注册成功！");
    }
}
