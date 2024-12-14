package com.example.minorProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject.models.Author;

public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {
    Author findByEmail(String email);
}
