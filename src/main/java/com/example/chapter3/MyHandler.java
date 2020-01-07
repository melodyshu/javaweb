package com.example.chapter3;

import java.lang.reflect.Method;

public class MyHandler {
    private Class<?> controllerClass;
    private Method actionMethod;

    public MyHandler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
