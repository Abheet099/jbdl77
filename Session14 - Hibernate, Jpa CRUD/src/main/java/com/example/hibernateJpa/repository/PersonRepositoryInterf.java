package com.example.hibernateJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hibernateJpa.models.Person;

@Repository
public interface PersonRepositoryInterf extends JpaRepository<Person, Integer> {
}
