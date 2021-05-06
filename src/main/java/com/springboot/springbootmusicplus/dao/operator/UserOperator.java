package com.springboot.springbootmusicplus.dao.operator;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.springbootmusicplus.dao.repository.UserMapper;
import com.springboot.springbootmusicplus.entity.User;
import org.apache.commons.collections.CollectionUtils;
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

    public User getUserInfoByUserId(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据 userName 查询用户信息
     * @param userName
     * @return
     */
    public List<User> getUserInfoByUserName(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        List<User> users = userMapper.selectList(wrapper);
        return CollectionUtils.isNotEmpty(users) ? users : null;
    }

}
