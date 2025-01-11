package com.example.minorProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.example.minorProject.enums.Genre;
import com.example.minorProject.models.Author;

@Data
@Builder
@AllArgsConstructor
public class BookDTO {
    private String name;
    private int cost;
    private Genre genre;
    private Author author;
}
