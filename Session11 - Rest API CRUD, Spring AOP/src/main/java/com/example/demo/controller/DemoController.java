package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Demo;
import com.example.demo.models.Employee;
import com.example.demo.service.PaymentServiceInterf;
import com.example.demo.serviceImpl.CardPaymentImpl;

@RestController
public class DemoController {

    // First way of dependency Injection - by autowired - Field Injection
//    @Autowired
//    Demo demoBySpring;


    // Second way of dependency injection - by constructor - constructor injection
    private Demo demoBySpring;

    public DemoController(Demo demoBySpring) {
        System.out.println("In DemoController1 constructor:" + demoBySpring);
        this.demoBySpring = demoBySpring;
        System.out.println("demoController object:" + this);
        System.out.println("demo object:" + this.demoBySpring);
    }

    // not allowed
//    @Autowired
//    Employee employee;

    // Example of @Bean
    @Autowired
    @Qualifier("getTemplate")
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("WalletPaymentImpl")
    PaymentServiceInterf walletType;

    @Autowired
    @Qualifier("CardPaymentImpl")
    PaymentServiceInterf cardTypeWithQualifier;

    @Autowired
    PaymentServiceInterf cardType;

    @Autowired
    CardPaymentImpl cardPayment;

    @GetMapping("/demo")
    public String printHelloWorld() {
//        DemoController demoController = new DemoController();
        return "Hello World";
    }

    // DemoController@4715ae33 - Created by spring
    // DemoController@623f36a0 - created by API call
    // DemoController@6a09d0bd - created by 2nd API call

    @GetMapping("/demo-obj")
    public void printDemoObject(@RequestParam int id) {
//        Demo demo = new Demo();
//        System.out.println(demoBySpring);
        System.out.println("In printDemoObject method ");
    }

    // com.example.demo.models.Demo@75961f16 - created by spring
    // com.example.demo.models.Demo@75961f16 - printed object
    // com.example.demo.models.Demo@75961f16


    // prototype scope
    // demo object:com.example.demo.models.Demo@2a2ef072 - Democontroller
    // demo object:com.example.demo.models.Demo@4207609e - Democontroller2

}
