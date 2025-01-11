package com.example.minorProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorProject.models.Student;
import com.example.minorProject.repository.StudentRepositoryInterf;
import com.example.minorProject.requests.StudentCreateRequest;

@Service
public class StudentService {

    @Autowired
    StudentRepositoryInterf studentRepositoryInterf;

    public Student saveStudent(StudentCreateRequest studentCreateRequest) {
        Student student = studentCreateRequest.toStudent();
        return studentRepositoryInterf.save(student);
    }

    public Optional<Student> getStudent(int studentId){
        return studentRepositoryInterf.findById(studentId);
    }
}
