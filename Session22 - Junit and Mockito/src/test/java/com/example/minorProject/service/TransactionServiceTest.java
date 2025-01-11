package com.example.minorProject.service;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.minorProject.enums.AccountStatus;
import com.example.minorProject.enums.Genre;
import com.example.minorProject.enums.TransactionType;
import com.example.minorProject.exceptions.TransactionServiceException;
import com.example.minorProject.models.Book;
import com.example.minorProject.models.Student;
import com.example.minorProject.models.Transaction;
import com.example.minorProject.repository.TransactionRepositoryInterf;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    StudentService studentService;

    @Mock
    BookService bookService;
    @Mock
    TransactionRepositoryInterf transactionRepositoryInterf;

    // Test case for issue transaction
    @Test
    @DisplayName("Test1: Perform Issue Transaction")
    @Order(1)
    public void transact_test() {
        int bookId = 1;
        int studentId = 1;
        int transactionId = 1;
        String externalTransactionId = UUID.randomUUID().toString();

        Optional<Student> studentOptional = Optional.ofNullable(Student.builder()
                .id(studentId).name("James").email("james@gmail.com")
                .phone("9876543246").address("India").accountStatus(AccountStatus.ACTIVE).build());

        Optional<Book> bookOptional = Optional.ofNullable(Book.builder()
                .id(bookId).name("Introduction to Java").genre(Genre.EDUCATIONAL)
                .cost(500).build());

        Optional<Book> assignedBookOptional = Optional.ofNullable(Book.builder()
                .id(bookId).name("Introduction to Java").genre(Genre.EDUCATIONAL)
                .cost(500).student(studentOptional.get())
                .build());

        Transaction transaction = Transaction.builder()
                .id(transactionId).externalId(externalTransactionId).transactionType(TransactionType.ISSUE)
                .student(studentOptional.get()).book(bookOptional.get()).build();

        // Stubbing
        when(studentService.getStudent(anyInt())).thenReturn(studentOptional);
        when(bookService.findBookbyId(bookId)).thenReturn(bookOptional);
        when(transactionRepositoryInterf.save(any())).thenReturn(transaction);
        when(bookService.save(any())).thenReturn(assignedBookOptional.get());

        String transactIdReturned = transactionService.transact(TransactionType.ISSUE.name(), studentId, bookId);

        // Verifies the external transaction Id
        Assertions.assertEquals(externalTransactionId, transactIdReturned);

        // Verify that transactionId shouldn't be empty
        Assertions.assertFalse(transactIdReturned.isEmpty());

        // Verifies the number of times particular method is called
        Mockito.verify(studentService, times(1)).getStudent(anyInt());
    }

    @Test
    public void transact_transactionServiceException(){
        int bookId = 1;
        int studentId = 1;

        Optional<Student> studentOptional = Optional.ofNullable(Student.builder()
                .id(studentId).name("James").email("james@gmail.com")
                .phone("9876543246").address("India").accountStatus(AccountStatus.ACTIVE).build());

        // Stubbing
        when(studentService.getStudent(anyInt())).thenReturn(studentOptional);
        when(bookService.findBookbyId(bookId)).thenReturn(Optional.empty());

        Assertions.assertThrows(TransactionServiceException.class, () -> {
            transactionService.transact(TransactionType.ISSUE.name(), studentId, bookId);
        });

    }
}
