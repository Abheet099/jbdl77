package com.example.minorProject.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorProject.models.Student;
import com.example.minorProject.requests.StudentCreateRequest;
import com.example.minorProject.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping()
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        return new ResponseEntity<>(studentService.saveStudent(studentCreateRequest), HttpStatus.CREATED);
    }

    // TODO: Add GET, PATCH, PUT, DELETE API's for student

}
