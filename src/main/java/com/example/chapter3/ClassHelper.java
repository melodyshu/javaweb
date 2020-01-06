package com.example.chapter3;

import java.util.HashSet;
import java.util.Set;

public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    static{
        String basePackage=ConfigHelper.getAppBasePackage();
        CLASS_SET=ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取包名下所有MyService类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet=new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (clz.isAnnotationPresent(MyService.class)){
                classSet.add(clz);
            }
        }
        return classSet;
    }

    /**
     * 获取包名下所有MyController类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet=new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (clz.isAnnotationPresent(MyController.class)){
                classSet.add(clz);
            }
        }
        return classSet;
    }

    /**
     * 获取包名下所有Bean类
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet=new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
}
