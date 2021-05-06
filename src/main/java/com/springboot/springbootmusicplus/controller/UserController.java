package com.springboot.springbootmusicplus.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootmusicplus.dao.operator.UserOperator;
import com.springboot.springbootmusicplus.dao.repository.UserMapper;
import com.springboot.springbootmusicplus.entity.User;
import com.springboot.springbootmusicplus.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private UserService userService;

    @GetMapping("/hello")
    @ApiOperation(value = "打印hello", httpMethod = "GET")
    public String sayHello() {
        System.out.println("Hello world！");
        User user = userService.getUserInfoByUserId(3333);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.forEach(System.out::println);
        log.info("user信息: {}", JSON.toJSONString(user));

        List<User> userList = userService.getUserInfoByUserName("101");
        log.info("userList: {}", JSON.toJSONString(userList));

        return "Hello world！";
    }
}
