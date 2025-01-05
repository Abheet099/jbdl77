package com.example.minorProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject.enums.TransactionType;
import com.example.minorProject.models.Book;
import com.example.minorProject.models.Student;
import com.example.minorProject.models.Transaction;

public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer> {
    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student, TransactionType transactionType);
}
