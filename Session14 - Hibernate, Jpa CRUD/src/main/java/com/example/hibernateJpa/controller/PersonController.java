package com.example.hibernateJpa.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibernateJpa.models.Person;
import com.example.hibernateJpa.models.request.CreatePersonRequest;
import com.example.hibernateJpa.service.PersonService;

@Slf4j
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping()
    public ResponseEntity<CreatePersonRequest> addPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest) {
        log.info("In PersonController.addPerson with createPersonRequest: {}", createPersonRequest);

        personService.createPersonStatic(createPersonRequest);

        // Set response headers
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Sample_header","cache");
        return new ResponseEntity<>(createPersonRequest,headers, HttpStatus.CREATED);

    }

    @GetMapping("/{personId}")
    public Person getPerson(@PathVariable("personId") int personId) {
        log.info("In PersonController.getPerson with personId: {}", personId);
        return personService.getPerson(personId);
    }

    @PutMapping("/{personId}")
    public Person updatePerson(@RequestBody CreatePersonRequest createPersonRequest,
                               @PathVariable("personId") int personId) {
        log.info("In PersonController.updatePerson with personId: {} and createPersonRequest: {}",
                personId, createPersonRequest);
        return personService.updatePerson(createPersonRequest, personId);
    }

    @DeleteMapping("/{personId}")
    public String deletePerson(@PathVariable("personId") int personId){
        personService.deletePerson(personId);
        return "Person deleted successfully";
    }

    @GetMapping("/all")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

}
