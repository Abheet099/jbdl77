package com.example.securityDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/home")
    public String greet(){
        return "Welcome to Spring boot demo app.";
    }

    //955271be-d2eb-42d7-9a96-51be455bd19b
    //f7cf3ba8-d6bc-4026-be15-a8ee889c9212

    // JSESSIONID - 313B76691CB28BF168B2F7C91915899E
    // 752DD92E3F2952E3004A2CDF113A2AEC

//    Request comes to backend
//    1) JSESSIONID sent from chrome is of unauthenticated session/user:
//        BE: Verifies the session id and redirect the user to /login api/page
//        FE: pass the credentials entered by user and calls the login with unauthenticated JSESSIONID
//        BE: Verifies the credentials entered by user and generate new authenticated JSESSIONID and pass in response header as set-cookie
//    2) JSESSIONID is of authenticated session/user:
//        BE: verify the session id and return the response of the requested resource

    @GetMapping("/developercode")
    public String developerCode(){
        return "Welcome developer!";
    }

    @GetMapping("/testcode")
    public String testCode(){
        return "Welcome Tester!";
    }
    @GetMapping("/generalcode")
    public String generalCode(){
        return "Welcome Public!";
    }
}
