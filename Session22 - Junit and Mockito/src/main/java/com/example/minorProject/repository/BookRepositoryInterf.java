package com.example.minorProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject.models.Book;

public interface BookRepositoryInterf extends JpaRepository<Book, Integer> {

    List<Book> findByName(String name);
    List<Book> findByAuthor_name(String authorName);
    List<Book> findByGenre(String genre);
    List<Book> findByCost(int cost);
}
