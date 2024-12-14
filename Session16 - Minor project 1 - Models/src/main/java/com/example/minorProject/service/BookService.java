package com.example.minorProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorProject.models.Author;
import com.example.minorProject.models.Book;
import com.example.minorProject.repository.AuthorRepositoryInterf;
import com.example.minorProject.repository.BookRepositoryInterf;
import com.example.minorProject.requests.BookCreateRequest;

@Service
public class BookService {

    @Autowired
    BookRepositoryInterf bookRepositoryInterf;

    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;

    public Book saveBook(BookCreateRequest bookCreateRequest) {
        // convert bookCreateRequest object to bookDTO
        // Perform business operation on dto object
        // create entity object with details from DTO

        Book book = bookCreateRequest.toBook();
        Author author = book.getAuthor();

        // Logic to check if author is already existing
        Author authorFromDb = authorRepositoryInterf.findByEmail(author.getEmail());

        if (authorFromDb == null) {
            authorFromDb = authorRepositoryInterf.save(author);
        }

        // Set to add foreign key of author in book table
        book.setAuthor(authorFromDb);
        // Validation to check book properties, validate if book is not duplicate

        return bookRepositoryInterf.save(book);
    }
}
