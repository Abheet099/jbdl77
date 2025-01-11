package com.example.minorProject.service;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.minorProject.enums.TransactionType;
import com.example.minorProject.exceptions.TransactionServiceException;
import com.example.minorProject.models.Book;
import com.example.minorProject.models.Student;
import com.example.minorProject.models.Transaction;
import com.example.minorProject.repository.TransactionRepositoryInterf;

@Service
@Transactional
public class TransactionService {

    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepositoryInterf transactionRepositoryInterf;

    @Value("${book.return.max.allowedDays}")
    int maxAllowedDays;

    public String transact(String transactionType, int studentId, int bookId) {

        // Two Type of Transaction: 1) Issue 2) Return

        // Issue
//        1) Student is valid
//        2) Book is valid
//        3) Book is available
//        4) Make the transaction
//        5) make the book unavailable

        // Return
//        1) Student is valid
//        2) Book is valid
//        3) Book is issues to that particular student only
//        4) Make the transaction
//        5) Make the book available

        // Check if student is valid
        Optional<Student> student = studentService.getStudent(studentId);

        if (student.isEmpty()) {
            throw new TransactionServiceException("Student is invalid.");
        }

        Optional<Book> book = bookService.findBookbyId(bookId);
        if (book.isEmpty()) {
            throw new TransactionServiceException("Book is invalid.");
        }

        if (TransactionType.valueOf(transactionType).equals(TransactionType.ISSUE)) {

            // Check if book is available
            if (book.get().getStudent() != null) {
                throw new TransactionServiceException("Book is not available for issue");
            }

            Transaction transaction = Transaction.builder()
                    .externalId(UUID.randomUUID().toString())
                    .transactionType(TransactionType.ISSUE)
                    .payment(book.get().getCost())
                    .book(book.get()).student(student.get())
                    .build();
            Transaction savedTransactionObj = transactionRepositoryInterf.save(transaction);

            // Set the student in book (Assign book to student)
            book.get().setStudent(student.get());
            bookService.save(book.get());

            return savedTransactionObj.getExternalId();
        } else if (TransactionType.valueOf(transactionType).equals(TransactionType.RETURN)) {

            // check if book is issued
            if(book.get().getStudent() == null){
                throw new TransactionServiceException("Book is not issued to any student");
            }

            // check if book is issued to same student
            if(book.get().getStudent().getId() != studentId){
                throw new TransactionServiceException("Book is not issued to this student");
            }

            Transaction issuetransaction = transactionRepositoryInterf.findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
                    book.get(), student.get(), TransactionType.ISSUE);

            calculateFine(issuetransaction);
            Transaction transaction = Transaction.builder()
                    .externalId(UUID.randomUUID().toString())
                    .transactionType(TransactionType.RETURN)
                    .payment(book.get().getCost()-calculateFine(issuetransaction))
                    .book(book.get()).student(student.get()).build();
            transactionRepositoryInterf.save(transaction);

            // Make the book available
            book.get().setStudent(null);
            bookService.save(book.get());

            return transaction.getExternalId();
        }
        return null;
    }

    private double calculateFine(Transaction issuetransaction) {

        long issueTime = issuetransaction.getCreatedOn().getTime();
        long returnTime = System.currentTimeMillis();

        long diffInMillis = returnTime - issueTime;
        long daysPassed = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        if(daysPassed > maxAllowedDays){
            return ((daysPassed-maxAllowedDays)*1);
        }
        return 0.0;
    }
}
