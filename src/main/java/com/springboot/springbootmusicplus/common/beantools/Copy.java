package com.springboot.springbootmusicplus.common.beantools;

/**
 * @author linliquan
 * @description: 把source转换为target需要的包装器类型或者原始类型
 * @create 2021/5/7 15:12
 */
public interface Copy {

    void copy(Object source, Object target, String ignoreProperties);

    void merge(Object source, Object target, String ignoreProperties);
}
