package com.hikolu.ecommerceapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @NotNull(message = "is required")
    @Size(min = 2, max = 20, message = "must contain from 2 to 20 symbols")
    @Column(name = "image")
    private String image;

    @NotNull(message = "is required")
    @Column(name = "name")
    private String name;

    @NotNull(message = "is required")
    @DecimalMin(value = "0.01", message = "must be greater than or equal to 0.01")
    @Column(name = "price")
    private double price;

    @Column(name = "owners")
    private int owners;

    // define constructors

    public Product() {
    }

    public Product(String image, String name, double price, int owners) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.owners = owners;
    }

    // getters and setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public int getOwners() {
        return owners;
    }

    public void setOwners(int owners) {
        this.owners = owners;
    }
}
