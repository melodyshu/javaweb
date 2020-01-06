package com.example.chapter3;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

public final class IocHelper {
    static {
        Map<Class<?>,Object> beanMap=BeanHelper.getBeanMap();
        if (!beanMap.isEmpty()){
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtils.isNotEmpty(beanFields)){
                    for (Field beanField : beanFields) {
                        
                    }
                }
            }
        }
    }
}
