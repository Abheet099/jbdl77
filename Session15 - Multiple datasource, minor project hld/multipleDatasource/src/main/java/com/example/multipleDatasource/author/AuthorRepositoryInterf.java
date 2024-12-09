package com.example.multipleDatasource.author;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositoryInterf extends JpaRepository<Author, Integer> {
}
