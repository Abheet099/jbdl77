package com.example.hibernateJpa.models.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import com.example.hibernateJpa.models.Person;

@Data
public class CreatePersonRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String dob;

    public Person to() {
        return Person.builder().firstName(this.firstName).lastName(lastName).dob(dob).build();
    }
}

