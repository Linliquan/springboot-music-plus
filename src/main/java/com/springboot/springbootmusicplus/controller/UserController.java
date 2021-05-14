package com.springboot.springbootmusicplus.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootmusicplus.common.beantools.Copier;
import com.springboot.springbootmusicplus.common.enums.FailEnums;
import com.springboot.springbootmusicplus.common.response.Response;
import com.springboot.springbootmusicplus.common.utils.UUIDUtil;
import com.springboot.springbootmusicplus.entity.User;
import com.springboot.springbootmusicplus.model.response.UserLoginResModel;
import com.springboot.springbootmusicplus.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

        log.info("用户注册：用户名：{}, 用户密码：{}", userName, userPassword);
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

    @PostMapping("/userLogin")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public Response<UserLoginResModel> userLogin(HttpServletRequest request) {

        // 取参数的方法，对应登录表单中的用户名name="user_name"
        String userName = request.getParameter("user_name");
        String userPassword = request.getParameter("user_password");
        log.info("用户登录：用户名：{}, 用户密码：{}", userName, userPassword);

        User user = userService.getUserInfo(userName, userPassword);
        if (user == null) {
            return Response.fail(FailEnums.NOT_EXISTS_ERROR.getCode(), "用户名或密码错误！");
        }
        UserLoginResModel userLoginResModel = new UserLoginResModel();
        Copier.copy(user, userLoginResModel);
        // 生成token
        userLoginResModel.setToken(UUIDUtil.getUUID());
        log.info("用户名：{} 登录成功！, msg:{}", userName, JSON.toJSONString(userLoginResModel));
        return Response.succ(userLoginResModel, "登录成功！");
    }

    @PostMapping("/resetUserPassword")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "修改密码", httpMethod = "POST")
    public Response<UserLoginResModel> resetUserPassword(HttpServletRequest request) {

        // 取参数的方法，对应登录表单中的用户名name="userName"
        String userName = request.getParameter("userName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        log.info("修改密码请求：用户名：{}, 旧密码：{}， 新密码：{}", userName, oldPassword, newPassword);

        User user = userService.getUserInfo(userName, oldPassword);
        if (user == null) {
            return Response.fail(FailEnums.NOT_EXISTS_ERROR.getCode(), "用户名或旧密码错误！");
        }
        // 修改用户密码
        try {
            // 外层使用默认的REQUIRED事务传播，内层使用Propagation.REQUIRES_NEW事务传播，实现内层回滚，外层也可以正常返回响应
            userService.updatePassword(userName, newPassword);
        } catch (Exception e) {
            return Response.fail(FailEnums.DB_OPERATOR_ERROR.getCode(), "修改密码失败！");
        }
        log.info("用户名：{} 修改密码成功！, 新密码:{}", userName, newPassword);
        return Response.succ(null, "修改密码成功！");
    }
}
