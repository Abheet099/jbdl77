package com.example.minorProject.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minorProject.models.MyUser;
import com.example.minorProject.models.Student;
import com.example.minorProject.repository.MyUserCacheRepository;
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

    // TODO: Add PATCH, PUT, DELETE API's for student

    @GetMapping()
    public Student getStudent(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();

        if(myUser.getStudent() == null){
            throw new RuntimeException("User is not a student");
        }

        return studentService.getStudent(myUser.getStudent().getId()).get();
    }
}
