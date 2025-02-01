package com.example.user_app;

public class ProductDTO {

    private Long id;
    private String name;
    private Double cost;

    public ProductDTO(Long id, String name, Double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public ProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCost() {
        return cost;
    }

    public ProductDTO setCost(Double cost) {
        this.cost = cost;
        return this;
    }
}
