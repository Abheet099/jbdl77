package com.example.minorProject.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorProject.models.Book;
import com.example.minorProject.requests.BookCreateRequest;
import com.example.minorProject.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        Book book = bookService.saveBook(bookCreateRequest);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
