package com.springboot.springbootmusicplus.dao.operator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.springbootmusicplus.dao.repository.UserMapper;
import com.springboot.springbootmusicplus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/30 17:05
 */
@Service
public class UserOperator extends BaseOperator<UserMapper, User> {

    @Resource
    private UserMapper userMapper;

    @Autowired
    UserOperator userOperator;

    public User getUserInfoByUserId(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> getUserList() {
        List<User> list = userOperator.list();
        return list;
    }


}
