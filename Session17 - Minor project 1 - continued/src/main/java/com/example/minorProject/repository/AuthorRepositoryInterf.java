package com.example.minorProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import com.example.minorProject.models.Author;

public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {

    // 1st: Don't need to write any query, just create JPA functions
    Author findByEmail(String email);

    // find the author above the age of 30, living in india and has name starting with p
    // select * from author where age>=30 and country = 'india' and name like 'p%';
    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(
            int age, String country, String prefix);

    // 2nd: JPQL - Jpa queries
    @Query(value = "select a from Author a where a.email= ?1")
    public Author getAuthorByEmail(String email);

    // 3rd - Native Query
    @NativeQuery(value = "select a from author a where a.email= ?1")
    public Author getAuthorByEmailUsingNative(String email);

}
