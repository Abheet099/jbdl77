package com.example.crud_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String dob;
}
