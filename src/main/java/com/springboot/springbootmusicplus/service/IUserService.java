package com.springboot.springbootmusicplus.service;

import com.springboot.springbootmusicplus.entity.User;

/**
 * @author linliquan
 * @description:
 * @create 2021/4/30 16:56
 */
public interface IUserService {

    User getUserInfoByUserId(Integer id);
}
