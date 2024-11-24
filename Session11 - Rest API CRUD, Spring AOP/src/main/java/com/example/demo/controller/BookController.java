package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Book;

@RequestMapping("/api/v1/book")
@RestController
public class BookController {
    private static Logger logger = LoggerFactory.getLogger(BookController.class);
    private HashMap<Integer, Book> bookHashMap = new HashMap<>();

    // addBook - POST - RequestBody
    // updateBook - PUT - RequestBody, Path variable
    // getBookDetails - GET - Request Param
    // deleteBook - DELETE - Path Variable
    // getAllBooks - GET - return book list

    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {
        logger.info("In BookController.addBook with book: {}", book);
        if (bookHashMap.containsKey(book.getId())) {
            logger.error("Book Already present!");
            return "Failed to add new book. Book already present";
        }
        bookHashMap.put(book.getId(), book);
        return "Book Added Successfully!";
    }

    @PutMapping("/update/{bookId}")
    public String updateBook(@RequestBody Book book, @PathVariable int bookId) {
        logger.info("In BookController.updateBook with book: {} and bookId: {}", book, bookId);
        if (bookHashMap.containsKey(book.getId())) {
            bookHashMap.put(bookId, book);
            return "Book details updated successfully";
        } else {
            logger.error("Book not found");
            return "Book not found";
        }
    }

    @GetMapping("/get")
    public Book getBookDetails(@RequestParam int bookId) {
        logger.info("In BookController.getBookDetails with bookId: {}", bookId);
        return bookHashMap.get(bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public String deleteBookDetails(@PathVariable int bookId) {
        logger.info("In BookController.deleteBookDetails with bookId: {}", bookId);
        bookHashMap.remove(bookId);
        return "Book deleted successfully";
    }

    @GetMapping("/all")
    public List<Book> getBooks() {
        logger.info("In BookController.getBooks");
        return new ArrayList<>(bookHashMap.values());
    }
}