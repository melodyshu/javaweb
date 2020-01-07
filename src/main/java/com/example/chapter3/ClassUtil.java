package com.example.chapter3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public final class ClassUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(ClassUtil.class);
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> clz;
        try {
            clz=Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure",e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return clz;
    }

    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet=new HashSet<>();
        try {
            Enumeration<URL> urls= getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()){
                URL url=urls.nextElement();
                System.out.println(url.getPath());
                if(url !=null){
                    String protocol=url.getProtocol();
                    if("file".equals(protocol)){
                        String packagePath=url.getPath();
                        addClass(classSet,packagePath,packageName);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files=new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
              return  (pathname.isFile() && pathname.getName().endsWith(".class"))||pathname.isDirectory();
            }
        });

        for (File file : files) {
           String fileName= file.getName();
           if(file.isFile()){
             String className= fileName.substring(0,fileName.lastIndexOf("."));
             className=packageName+"."+className;
             doAddClass(classSet,className);
           }else{
               String SubPackagePath=packagePath+"/"+fileName;
               String subPackageName=packageName+"."+fileName;
               addClass(classSet,packagePath,subPackageName);
           }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
       Class<?> clz= loadClass(className,false);
       classSet.add(clz);
    }

    public static void main(String[] args) {
        getClassSet("com.example.chapter3");
    }
}
