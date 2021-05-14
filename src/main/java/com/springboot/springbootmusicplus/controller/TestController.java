package com.springboot.springbootmusicplus.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootmusicplus.common.beantools.Copier;
import com.springboot.springbootmusicplus.common.enums.FailEnums;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.entity.User;
import com.springboot.springbootmusicplus.hepler.checker.UserRegisterChecker;
import com.springboot.springbootmusicplus.model.request.SongRearchRequest;
import com.springboot.springbootmusicplus.model.request.UserRegisterRequest;
import com.springboot.springbootmusicplus.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/8 15:46
 */
@Slf4j
@RestController
@Api(tags = "用户测试")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegisterChecker userRegisterChecker;

    @GetMapping("/hello")
    @ApiOperation(value = "打印hello", httpMethod = "GET")
    public String sayHello() {
        User user = userService.getUserInfoByUserId(7);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.forEach(System.out::println);
        log.info("user信息: {}", JSON.toJSONString(user));

        List<User> userList = userService.getUserInfoByUserName("101");
        log.info("userList: {}", JSON.toJSONString(userList));
        User cUser = new User();
        Copier.copy(user, cUser);
        log.info("class copy Test, cUser: {}", JSON.toJSONString(cUser));

        User sUser = new User();
        BeanUtils.copyProperties(user, sUser);
        log.info("spring copy Test, cUser: {}", JSON.toJSONString(cUser));

        return "Hello world！";
    }

    @PostMapping("/parameterTest")
    @Validated
    @ApiOperation(value = "参数检验测试", httpMethod = "POST")
    public String parameterTest(@Valid @RequestBody SongRearchRequest request) {
        log.info("必传 songName: {}", request.getSongName());
        return request.getSongName();
    }

    @PostMapping("/checkParameterTest")
    @ApiOperation(value = "检查参数测试", httpMethod = "POST")
    public Response<String> checkParameterTest(@RequestBody UserRegisterRequest request) {
        log.info("必传 userName: {}, userPassword: {}", request.getUserName(), request.getUserPassword());
        String errorMessage = userRegisterChecker.checkUserRegisterReq(request);
        if (StringUtils.isNotBlank(errorMessage)) {
            return Response.fail(FailEnums.PARAM_ERROR.getCode(), errorMessage);
        }
        return Response.succ(request.getUserName() + " " + request.getUserPassword());
    }

}
