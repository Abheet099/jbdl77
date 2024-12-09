package com.example.multipleDatasource.author;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController {

    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;

    @Autowired
    AuthorService authorService;

    @PostMapping()
    public ResponseEntity<?> createPerson(@RequestBody @Valid Author author) {
        System.out.println(authorRepositoryInterf);
        Author savedAuthor = authorRepositoryInterf.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }
}
