package com.example.demo.models;

public class Book {

    private int id;
    private String name;
    private int cost;
    private String author;

    public Book() {
    }

    public Book(int id, String name, int cost, String author) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public Book setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", author='" + author + '\'' +
                '}';
    }
}
