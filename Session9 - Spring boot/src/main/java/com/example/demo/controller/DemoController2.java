package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import com.example.demo.models.Demo;

@Controller
public class DemoController2 {

    private final Demo demo;

    public DemoController2(Demo demo) {
        System.out.println("In DemoController2 constructor:" + demo);
        this.demo = demo;
    }
}
