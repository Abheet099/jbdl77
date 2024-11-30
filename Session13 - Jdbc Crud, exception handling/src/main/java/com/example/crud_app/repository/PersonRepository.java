package com.example.crud_app.repository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import javax.swing.plaf.nimbus.State;

import com.example.crud_app.model.Person;

@Repository
@Slf4j
public class PersonRepository {

    Connection connection;

    private PreparedStatement preparedStatement;

    public PersonRepository(Connection connection) throws SQLException {
        this.connection = connection;
        createTable();
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists person(id int primary key auto_increment, first_name varchar(30), " +
                "last_name varchar(30), age int, dob varchar(12))");
    }

    // Save the data statically using statement and fixed static values
    public void savePersonStatic(Person person) {
        try {
            Statement statement = connection.createStatement();
            int insertResult = statement.executeUpdate("insert into person(id, first_name, last_name, dob, age) " +
                    "VALUES(1, 'Peter', 'David', '1998-01-07', 21)");

            log.info("Insert statement result: {}", insertResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePerson(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into person(first_name, last_name, dob, age)" +
                    " VALUES (?,?,?,?)");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getDob());
            preparedStatement.setInt(4, person.getAge());

            int insertResult = preparedStatement.executeUpdate();

            log.info("Insert statement result: {}", insertResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPerson(int personId) {
        try {
            Person person = null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
            preparedStatement.setInt(1, personId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                person = getPersonFromResultset(resultSet);
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePerson(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update person set first_name =?," +
                    "last_name =?, age=?, dob=? where id=?");

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(4, person.getDob());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setInt(5, person.getId());

            int updateResult = preparedStatement.executeUpdate();

            log.info("Update statement result: {}", updateResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePerson(int personId) {
        try {
            this.preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, personId);

            int deleteResult = preparedStatement.executeUpdate();
            log.info("Delete result: {}", deleteResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Person> getAllPersons() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");

            List<Person> personList = new ArrayList<>();
            while (resultSet.next()) {
                Person person = getPersonFromResultset(resultSet);
                personList.add(person);
            }
            return personList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Person getPersonFromResultset(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .age(resultSet.getInt(4))
                .dob(resultSet.getString(5))
                .build();
    }
}
