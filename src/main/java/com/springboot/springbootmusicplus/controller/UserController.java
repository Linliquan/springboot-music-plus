package com.springboot.springbootmusicplus.controller;

import com.springboot.springbootmusicplus.common.enums.FailEnums;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.entity.User;
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
    public Response<Boolean> userRegister(@RequestParam(required = false) String userName,
                                          @RequestParam(required = false) String userPassword) {

        log.info("用户注册：用户名：{}, 用户密码：{}", userName ,userPassword);
        List<User> userList = userService.getUserInfoByUserName(userName);
        if (CollectionUtils.isNotEmpty(userList)) {
            return Response.fail(FailEnums.EXISTS_ERROR.getCode(), "用户名已存在！");
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        boolean result = userService.insertUser(user);
        if (!result) {
            return Response.fail(FailEnums.DATA_ERROR.getCode(), "插入用户失败！");
        }
        log.info("用户名：{}, 用户注册成功！", userName);
        return Response.succ(null, "用户注册成功！");
    }
}
