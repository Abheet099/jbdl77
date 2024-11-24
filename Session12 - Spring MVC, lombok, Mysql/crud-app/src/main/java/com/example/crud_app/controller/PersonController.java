package com.example.crud_app.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud_app.model.CreatePersonRequest;
import com.example.crud_app.model.Person;
import com.example.crud_app.service.PersonService;

@Slf4j
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping()
    public void addPerson(@RequestBody CreatePersonRequest createPersonRequest) {
        log.info("In PersonController.addPerson with createPersonRequest: {}", createPersonRequest);
        personService.createPersonStatic(createPersonRequest);

    }


}
