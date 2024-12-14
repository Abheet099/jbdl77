package com.example.minorProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject.models.Book;

public interface BookRepositoryInterf extends JpaRepository<Book, Integer> {
}
