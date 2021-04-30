package com.springboot.springbootmusicplus.controller;

import com.springboot.springbootmusicplus.entity.User;
import com.springboot.springbootmusicplus.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/30 15:55
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("Hello world！");
        User user = userServiceImpl.getUserInfoByUserId(10);
        System.out.println(user);
        return "Hello world！";
    }
}
