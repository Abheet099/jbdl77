package com.example.hibernateJpa.service;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.hibernateJpa.exceptions.BadPersonRequestException;
import com.example.hibernateJpa.models.Person;
import com.example.hibernateJpa.models.request.CreatePersonRequest;
import com.example.hibernateJpa.repository.PersonRepositoryInterf;

@Service
@Slf4j
@ControllerAdvice
public class PersonService {

    @Autowired
    PersonRepositoryInterf personRepositoryInterf;

    public void createPersonStatic(CreatePersonRequest createPersonRequest) {
        Person person = createPersonRequest.to();

        if (person.getAge() == null) {
            person.setAge(calculateAgeFromDOB(person.getDob()));
        }
        personRepositoryInterf.save(person);
    }

    private Integer calculateAgeFromDOB(String dob) {
        if (!StringUtils.hasText(dob)) {
            return null;
        }
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dobDate, currentDate).getYears();
    }

    public Person getPerson(int personId) {
        return personRepositoryInterf.findById(personId).get();
    }

    public Person updatePerson(CreatePersonRequest createPersonRequest, int personId) {

        // Validate
        personRepositoryInterf.findById(personId).orElseThrow(
                () -> new BadPersonRequestException("Person with id=" + personId + " is not available"));

        Person newPerson = createPersonRequest.to();
        newPerson.setId(personId);

        if (newPerson.getAge() == null) {
            newPerson.setAge(calculateAgeFromDOB(newPerson.getDob()));
        }
        personRepositoryInterf.save(newPerson);
        return newPerson;
    }

    public void deletePerson(int personId) {
        personRepositoryInterf.deleteById(personId);
    }

    public List<Person> getAllPersons() {
        return personRepositoryInterf.findAll();
    }
}
