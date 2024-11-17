package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Demo;

@Controller
public class DemoController2 {
    @Autowired
    @Qualifier("getTemplate2")
    RestTemplate restTemplate;


    private final Demo demo;

    public DemoController2(Demo demo) {
        System.out.println("In DemoController2 constructor:" + demo);
        this.demo = demo;
    }
}
