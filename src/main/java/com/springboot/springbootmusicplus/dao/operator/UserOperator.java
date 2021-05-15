package com.springboot.springbootmusicplus.dao.operator;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.springboot.springbootmusicplus.dao.repository.UserMapper;
import com.springboot.springbootmusicplus.entity.User;
import org.apache.commons.collections.CollectionUtils;
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

    /**
     * 根据 id 查询用户信息
     * @param id
     * @return
     */
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

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    public boolean insertUser(User user) {
        return userMapper.insertSelective(user) > 0;
    }

    /**
     * 根据用户名和密码查询用户信息
     * @param userName
     * @param userPassword
     * @return
     */
    public User getUserInfo(String userName, String userPassword) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        wrapper.eq(User::getUserPassword, userPassword);
        List<User> list = userMapper.selectList(wrapper);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新用户密码
     * @param userName
     * @return
     */
    public boolean updatePassword(String userName, String newPassword) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUserName, userName);
        User user = new User();
        user.setUserPassword(newPassword);
        return userMapper.update(user, wrapper) > 0;
//        // 回滚测试
//        userMapper.update(user, wrapper);
//        throw new RuntimeException();
    }

}
