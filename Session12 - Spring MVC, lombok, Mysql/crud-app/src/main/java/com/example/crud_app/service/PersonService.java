package com.example.crud_app.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.crud_app.model.CreatePersonRequest;
import com.example.crud_app.model.Person;
import com.example.crud_app.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void createPersonStatic(CreatePersonRequest createPersonRequest) {
        Person person = createPersonRequest.to();
        if (person.getAge() == null) {
            person.setAge(calculateAgeFromDOB(person.getDob()));
        }
        personRepository.savePerson(person);
    }

    private Integer calculateAgeFromDOB(String dob) {
        if (!StringUtils.hasText(dob)) {
            return null;
        }
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dobDate, currentDate).getYears();
    }
}
