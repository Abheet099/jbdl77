package com.example.demo.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Demo {
    private int id;
    private String name;

    public Demo() {
        System.out.println("demo object:" + this);
    }
}
