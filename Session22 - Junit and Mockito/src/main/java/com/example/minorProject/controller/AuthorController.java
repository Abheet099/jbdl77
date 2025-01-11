package com.example.minorProject.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorProject.models.Author;
import com.example.minorProject.models.Book;
import com.example.minorProject.requests.BookCreateRequest;
import com.example.minorProject.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@Valid @RequestBody Author author){
        Author authorFromDb = authorService.saveAuthor(author);
        return new ResponseEntity<>(authorFromDb, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Author> getAuthor(@RequestParam("authorId") int authorId){
        return new ResponseEntity<>(authorService.getAuthor(authorId), HttpStatus.OK);
    }
}
