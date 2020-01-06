package com.example.chapter3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class BeanHelper {
    private static final Map<Class<?>,Object> BEAN_MAP=new HashMap<>();
    static {
        Set<Class<?>> beanSet=ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanSet) {
           Object object= ReflectionUtil.newInstance(beanClass);
           BEAN_MAP.put(beanClass,object);
        }
    }

    /**
     * 获取Bean映射
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> clz){
        if(!BEAN_MAP.containsKey(clz)){
            throw new RuntimeException("bean获取失败:"+clz);
        }
        return (T)BEAN_MAP.get(clz);
    }
}
