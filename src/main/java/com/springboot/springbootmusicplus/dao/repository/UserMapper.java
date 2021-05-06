package com.springboot.springbootmusicplus.dao.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.springbootmusicplus.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Integer userId);

    @Override
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}