package com.example.crud_app.model;

import lombok.Data;

@Data
public class CreatePersonRequest {

    private String firstName;
    private String lastName;
    private String dob;

    public Person to() {
        return Person.builder().firstName(this.firstName).lastName(lastName).dob(dob).build();
    }
}
