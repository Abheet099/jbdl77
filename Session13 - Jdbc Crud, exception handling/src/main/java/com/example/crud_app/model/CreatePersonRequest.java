package com.example.crud_app.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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
