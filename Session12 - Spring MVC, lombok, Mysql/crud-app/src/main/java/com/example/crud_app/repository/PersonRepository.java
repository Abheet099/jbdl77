package com.example.crud_app.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.crud_app.model.Person;

@Repository
public class PersonRepository {

    @Autowired
    Connection connection;

    public void savePerson(Person person) {
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
