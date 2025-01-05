package com.example.minorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorProject.models.Author;
import com.example.minorProject.repository.AuthorRepositoryInterf;

@Service
public class AuthorService {

    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;


    public Author saveAuthor(Author author) {
        return authorRepositoryInterf.save(author);
    }

    public Author getAuthor(int authorId){
        return authorRepositoryInterf.findById(authorId).get();
    }
}
