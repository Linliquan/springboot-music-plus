package com.springboot.springbootmusicplus.common.beantools;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 15:14
 */
@Slf4j
public class Generator {
    private static final AtomicInteger classNameIndex = new AtomicInteger(1000);
    private static final String packageName = Copier.class.getPackage().getName();

    private final String SOURCE = "s";
    private final String TARGET = "t";
    private Class source;
    private Class target;


    public void setSource(Class source) {
        this.source = source;
    }

    public void setTarget(Class target) {
        this.target = target;
    }


    private String generateBegin(String methodName) {
        // 生成方法签名public void copy(Object s1, Object t1) {
        String beginSource = "public void " + methodName + "(Object " + SOURCE + "1, Object " + TARGET + "1 ,String ignoreProperties) {\n";
        // 强制转换源对象
        String convertSource = "\t" + source.getName() + " " + SOURCE + " = " + "(" + source.getName() + ")" + SOURCE + "1;\n";
        // 强制转换目标对象
        String convertTarget = "\t" + target.getName() + " " + TARGET + " = " + "(" + target.getName() + ")" + TARGET + "1;\n";
        beginSource += convertSource + convertTarget;
        return beginSource;
    }

    private String generateEnd() {
        return "}\n";
    }

    private List<String> generateBody(boolean isCheckNull) {
        List<String> propSources = Lists.newArrayList();
        PropertyDescriptor[] getters = getPropertyDescriptors(source);
        PropertyDescriptor[] setters = getPropertyDescriptors(target);

        Map<String, PropertyDescriptor> getterMap = Maps.newHashMap();
        for (PropertyDescriptor getter : getters) {
            getterMap.put(getter.getName(), getter);
        }

        for (PropertyDescriptor setter : setters) {
            PropertyDescriptor getter = getterMap.get(setter.getName());
            if (!checkCanGenSource(setter, getter)) {
                continue;
            }
            Method readMethod = getter.getReadMethod();
            Method writeMethod = setter.getWriteMethod();
            String readMethodName = readMethod.getName();
            String writerMethodName = writeMethod.getName();
            //基本类型不需要判断null 不然 int !=null 语法报错
            if (getter.getPropertyType().isPrimitive()) {
                isCheckNull = false;
            }
            //判断 两个属性是不是一个类型 是否能进行复制，不然出现cast exception

            if (compatible(getter, setter)) {
                propSources.add(genPropertySource(writerMethodName, getter, isCheckNull));
            } else {
                warnCantConvert(setter, getter);

            }


        }
        return propSources;
    }


    private String genPropertySource(String writerMethodName, PropertyDescriptor getter, boolean isCheckNull) {
        String getterSource = SOURCE + "." + getter.getReadMethod().getName() + "()";
        String setMethod = "\t" + TARGET + "." + writerMethodName + "(" + getterSource + ");\n";
        //需要判断null
        if (isCheckNull) {
            setMethod = "\tif( " + getterSource + " != null)\n" + "{" + setMethod + "};\n";
        }
        return "\t if( ignoreProperties==null || !ignoreProperties.contains(\"" + getter.getName() + "\")) \r\n {" + setMethod + "}\n";
    }

    private void warnCantConvert(PropertyDescriptor setter, PropertyDescriptor getter) {

        log.warn("[Properties] convert fail " +
                getter.getReadMethod().getDeclaringClass().getSimpleName() +
                "." +
                getter.getName() +
                "(" +
                getter.getPropertyType() +
                ") -> " +
                setter.getWriteMethod().getDeclaringClass().getSimpleName() +
                "." +
                setter.getName() +
                "(" +
                setter.getPropertyType() +
                ").");

    }

    /**
     * 检查是否可以生成源代码
     */
    private boolean checkCanGenSource(PropertyDescriptor setter, PropertyDescriptor getter) {

        // 检查getter是否存在
        if (getter == null || getter.getReadMethod() == null) {
            return false;
        }
        // 检查setter的写方法是否存在
        return !(setter == null || setter.getWriteMethod() == null);
    }


    public Class<Copy> generate() {
        StringBuilder copybuilder = new StringBuilder();

        //生产COPY代码
        copybuilder.append(generateBegin("copy"));
        for (String ps : generateBody(false)) {
            copybuilder.append(ps);
        }

        copybuilder.append(generateEnd());
        //生产merge代码
        StringBuilder mergebuilder = new StringBuilder();

        mergebuilder.append(generateBegin("merge"));
        for (String ps : generateBody(true)) {
            mergebuilder.append(ps);
        }

        mergebuilder.append(generateEnd());

        log.warn("copy code:" + copybuilder.toString());
        log.warn("merge code:" + mergebuilder.toString());

        ClassPool pool = ClassPool.getDefault();
        /**
         * The default ClassPool returned by a static method ClassPool.getDefault() searches the same path that
         * the underlying JVM (Java virtual machine) has. If a program is running on a web application server
         * such as JBoss and Tomcat, the ClassPool object may not be able to find user classes since such
         * a web application server uses multiple class loaders as well as the system class loader. In that case,
         * an additional class path must be registered to the ClassPool.
         */
        ClassClassPath classPath = new ClassClassPath(getClass());
        pool.insertClassPath(classPath);
        CtClass cc = pool.makeClass(packageName + ".CopierImpl" + classNameIndex.incrementAndGet());

        try {
            cc.addInterface(pool.get(Copy.class.getName()));
            CtMethod copym = CtNewMethod.make(copybuilder.toString(), cc);
            cc.addMethod(copym);
            CtMethod mergem = CtNewMethod.make(mergebuilder.toString(), cc);
            cc.addMethod(mergem);
            return (Class<Copy>) cc.toClass(getDefaultClassLoader(), null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean compatible(PropertyDescriptor getter, PropertyDescriptor setter) {
        return setter.getPropertyType().isAssignableFrom(getter.getPropertyType());
    }

    private ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignored) {
        }
        if (cl == null) {
            cl = getClass().getClassLoader();
        }
        return cl;
    }

    public PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            return beanInfo.getPropertyDescriptors();
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }
}
