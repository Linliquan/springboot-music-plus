package com.springboot.springbootmusicplus.dao.operator;

import com.springboot.springbootmusicplus.dao.repository.UserMapper;
import com.springboot.springbootmusicplus.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/30 17:05
 */
@Service
public class UserOperator {

    @Resource
    private UserMapper userMapper;

    public User getUserInfoByUserId(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
