package com.springboot.springbootmusicplus.common.beantools;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/7 15:15
 */
public class Key {
    private Class<?> fromClass;
    private Class<?> toClass;

    public Key(Class<?> fromClass, Class<?> toClass) {
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    public Key(Class<?> fromClass, Class<?> toClass, String[] ignoreProperties) {
        super();
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Key key = (Key) o;
        return fromClass.equals(key.fromClass)
                && toClass.equals(key.toClass);
    }

    @Override
    public int hashCode() {
        int h = fromClass.hashCode();
        h = 31 * h + toClass.hashCode();
        return h;
    }
}
