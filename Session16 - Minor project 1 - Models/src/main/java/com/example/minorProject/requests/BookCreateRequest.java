package com.example.minorProject.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.example.minorProject.enums.Genre;
import com.example.minorProject.models.Author;
import com.example.minorProject.models.Book;

@Data
@Builder
@AllArgsConstructor
public class BookCreateRequest {

    @NotBlank
    private String name;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    @NotNull
    private Author author;

    public Book toBook() {
        return Book.builder().name(name)
                .cost(cost).genre(genre)
                .author(this.author).build();
    }

}
