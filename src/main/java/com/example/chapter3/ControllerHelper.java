package com.example.chapter3;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ControllerHelper {
    private static final Map<MyRequest,MyHandler> ACTION_MAP=new HashMap<>();
    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)){
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)){
                    for (Method method : methods) {
                        if(method.isAnnotationPresent(MyAction.class)){
                            MyAction action = method.getAnnotation(MyAction.class);
                            String mapping=action.value();
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array=mapping.split(":");
                                if(ArrayUtils.isNotEmpty(array) && array.length==2){
                                    String requestMethod=array[0];
                                    String requestPath=array[1];
                                    MyRequest myRequest = new MyRequest(requestMethod, requestPath);
                                    MyHandler myHandler = new MyHandler(controllerClass, method);
                                    ACTION_MAP.put(myRequest,myHandler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static MyHandler getMyHandler(String requestMethod,String requestPath){
        MyRequest myRequest = new MyRequest(requestMethod, requestPath);
        return ACTION_MAP.get(myRequest);
    }

    public static void main(String[] args) {
        System.out.println("**************************");
    }
}
