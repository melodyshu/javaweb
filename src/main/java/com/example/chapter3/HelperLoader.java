package com.example.chapter3;

public final class HelperLoader {
    public static void init(){
        Class<?>[] classList={ClassHelper.class,BeanHelper.class,IocHelper.class,ControllerHelper.class};
        for (Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName(),false);
        }
    }

    public static void main(String[] args) {
        init();
    }
}
