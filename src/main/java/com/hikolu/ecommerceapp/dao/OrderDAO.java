package com.hikolu.ecommerceapp.dao;

import com.hikolu.ecommerceapp.model.Order;

import java.util.List;

public interface OrderDAO {

    // read
    List<Order> getOrders();

    Order getOrderById(int id);

    Order getOrderByUserId(int userId);

    // create and update
    Order saveOrder(Order order);

    // delete
    String deleteOrderById(int id);
}
