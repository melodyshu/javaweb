package com.example.chapter3;

@MyController
public class HelloController {
    @MyAction("get:/hi")
    public Data createSubmit(Param param){
        Data data=new Data();
        data.setModel("hello world!");
        return data;
    }
}
