package com.example.chapter3;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

public class MyRequest {
    private String requestMethod;
    private String requestPath;

    public MyRequest(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj);
    }

    public static void main(String[] args) {
        Map<MyRequest,String> map=new HashMap<>();
        MyRequest myRequest1=new MyRequest("x","x");
        map.put(myRequest1,"aaa");

        MyRequest myRequest2=new MyRequest("x","x");
        System.out.println(map.get(myRequest2));
    }
}
