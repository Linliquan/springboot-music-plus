package com.springboot.springbootmusicplus.dao.operator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/6 15:13
 */
public abstract class BaseOperator<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
