package com.example.minorProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject.models.MyUser;

public interface MyUserRepositoryInterf extends JpaRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
