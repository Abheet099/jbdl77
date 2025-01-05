package com.example.minorProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minorProject.models.Admin;

public interface AdminRepositoryInterf extends JpaRepository<Admin, Integer> {
}
