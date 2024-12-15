package com.example.minorProject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorProject.enums.BookFilterType;
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

//        authorRepositoryInterf.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(
//                30, "india", "j");

        if (authorFromDb == null) {
            authorFromDb = authorRepositoryInterf.save(author);
        }

        // Set to add foreign key of author in book table
        book.setAuthor(authorFromDb);
        // Validation to check book properties, validate if book is not duplicate

        return bookRepositoryInterf.save(book);
    }

    public List<Book> findBooks(BookFilterType bookFilterType, String filterValue) {

        switch(bookFilterType){
            case NAME -> {
                return bookRepositoryInterf.findByName(filterValue);
            }
            case ID -> {
                return bookRepositoryInterf.findAllById(new ArrayList<>(Integer.parseInt(filterValue)));
            }
            case AUTHOR_NAME -> {
                return bookRepositoryInterf.findByAuthor_name(filterValue);
            }
            case COST ->
                bookRepositoryInterf.findByCost(Integer.parseInt(filterValue));
            case GENRE -> {
                return bookRepositoryInterf.findByGenre(filterValue);
            }
        }
        return new ArrayList<>();
    }

    public Optional<Book> findBookbyId(int bookId){
        return bookRepositoryInterf.findById(bookId);
    }
    public Book save(Book book){
        return bookRepositoryInterf.save(book);
    }
}
