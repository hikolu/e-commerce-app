package com.hikolu.ecommerceapp.dto;

public class ProductDTOMain {

    // define needed fields
    private String name;
    private double price;
    private String image;

    // define constructors
    public ProductDTOMain() {

    }

    public ProductDTOMain(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    // define getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
