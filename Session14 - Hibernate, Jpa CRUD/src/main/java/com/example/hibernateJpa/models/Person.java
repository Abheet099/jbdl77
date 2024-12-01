package com.example.hibernateJpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.Hibernate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "person_tab")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    // AUTO
//    Hibernate: select next_val as id_val from person_tab_seq for update
//    Hibernate: update person_tab_seq set next_val= ? where next_val=?
//    Hibernate: insert into person_tab (age,dob,fname,last_name,id) values (?,?,?,?,?)

    // IDENTITY
//    Hibernate: insert into person_tab (age, dob, fname, last_name) values (?,?,?,?)

    @Column(name = "fname", unique = true, updatable = false)
    private String firstName;
    private String lastName;

    @Column(updatable = false)
    private Integer age;
    private String dob;

    // Ignore this field in database
    @Transient
    private String region;

}
