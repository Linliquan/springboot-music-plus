package com.springboot.springbootmusicplus.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootmusicplus.entity.User;
import com.springboot.springbootmusicplus.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private UserService userServiceImpl;

    @RequestMapping("/hello")
    @ApiOperation(value = "打印hello", httpMethod = "POST")
    public String sayHello() {
        System.out.println("Hello world！");
        User user = userServiceImpl.getUserInfoByUserId(10);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.forEach(System.out::println);
        log.info("user信息: {}", JSON.toJSONString(user));
        return "Hello world！";
    }
}
