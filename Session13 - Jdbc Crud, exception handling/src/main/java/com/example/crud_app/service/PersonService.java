package com.example.crud_app.service;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.crud_app.exceptions.BadPersonRequestException;
import com.example.crud_app.model.CreatePersonRequest;
import com.example.crud_app.model.Person;
import com.example.crud_app.model.response.ErrorResponse;
import com.example.crud_app.repository.PersonRepository;

@Service
@Slf4j
@ControllerAdvice
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void createPersonStatic(CreatePersonRequest createPersonRequest) {
        Person person = createPersonRequest.to();

        // Perform the field validation
//        if(person.getFirstName() == null || person.getFirstName().isEmpty()){
//            log.error("firstName is required field");
//            return;
//        }

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

    public Person getPerson(int personId) {
        return personRepository.getPerson(personId);
    }

    public Person updatePerson(CreatePersonRequest createPersonRequest, int personId) {

        Person person = personRepository.getPerson(personId);

        if (person == null) {
            throw new BadPersonRequestException("Person with id=" + personId + " is not available");
        }
        Person newPerson = createPersonRequest.to();
        newPerson.setId(personId);

        if (newPerson.getAge() == null) {
            newPerson.setAge(calculateAgeFromDOB(newPerson.getDob()));
        }
        personRepository.updatePerson(newPerson);
        return newPerson;
    }

    public void deletePerson(int personId) {
        personRepository.deletePerson(personId);
    }

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }


}
