package com.example.minorProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject.models.Student;

public interface StudentRepositoryInterf extends JpaRepository<Student, Integer> {
}
