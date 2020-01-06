package com.example.chapter3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     * @param clz
     * @return
     */
    public static Object newInstance(Class<?> clz){
        Object instance=null;
        try {
            instance=clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     */
    public static Object invokeMethod(Object object, Method method,Object... args){
        method.setAccessible(true);
        Object result=null;
        try {
            result=method.invoke(object,args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object object, Field field,Object value){

        try {
            field.setAccessible(true);
            field.set(object,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
