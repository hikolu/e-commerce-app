package com.hikolu.ecommerceapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "username")
    private String username;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "bill")
    private double bill;

    // define constructors

    public Order() {
    }

    public Order(String username, int quantity, int productId, double bill) {
        this.username = username;
        this.quantity = quantity;
        this.productId = productId;
        this.bill = bill;
    }

    // define getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return orderId;
    }

    public void setUserId(String username) {
        this.username = username;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }
}
