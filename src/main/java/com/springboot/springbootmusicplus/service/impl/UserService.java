package com.springboot.springbootmusicplus.service.impl;

import com.springboot.springbootmusicplus.dao.operator.UserOperator;
import com.springboot.springbootmusicplus.entity.User;
import com.springboot.springbootmusicplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/30 16:59
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserOperator userOperator;

    @Override
    public User getUserInfoByUserId(Integer id) {
        return userOperator.getUserInfoByUserId(id);
    }

    public List<User> getUserInfoByUserName(String userName) {
        return userOperator.getUserInfoByUserName(userName);
    }
}
