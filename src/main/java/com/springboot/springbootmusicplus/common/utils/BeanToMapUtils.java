package com.springboot.springbootmusicplus.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 10:23
 */
@Slf4j
public class BeanToMapUtils {


    /**
     * 将一个 map 对象转化为一个 JavaBean
     *
     * @param clazz 要转化的类型
     * @param map   包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */
    public static <T> T toBean(Class<T> clazz, Map<?, ?> map) {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            // 创建 JavaBean 对象
            obj = clazz.newInstance();

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if ("".equals(value)) {
                        value = null;
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    try {
                        descriptor.getWriteMethod().invoke(obj, args);
                    } catch (InvocationTargetException e) {
                        // System.out.println("字段映射失败");
                        log.error(e.toString());
                    }
                }
            }
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("实例化 JavaBean 失败");
        } catch (IntrospectionException e) {
            log.error("分析类属性失败");
        } catch (IllegalArgumentException e) {
            log.error("映射错误");
        }
        return obj;
    }

    /**
     * 将一个 JavaBean 对象转化为一个 map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的 map 对象
     */
    public static Map<String, Object> toMap(Object bean) {
        Class<?> clazz = bean.getClass();
        Map<String, Object> returnMap = new HashMap<>();
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (!"class".equals(propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result;
                    result = readMethod.invoke(bean);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    }
                }
            }
        } catch (IntrospectionException e) {
            log.error("分析类属性失败");
        } catch (IllegalAccessException e) {
            log.error("实例化 JavaBean 失败");
        } catch (IllegalArgumentException e) {
            log.error("映射错误");
        } catch (InvocationTargetException e) {
            log.error("调用属性的 getter 方法失败");
        }
        return returnMap;
    }
}
