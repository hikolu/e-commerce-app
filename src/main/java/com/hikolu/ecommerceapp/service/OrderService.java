package com.hikolu.ecommerceapp.service;

import com.hikolu.ecommerceapp.model.Order;

import java.util.List;

public interface OrderService {

    // get all orders
    List<Order> getOrders();

    // get an order by id
    Order getOrderById(int id);

    // find order by id
    List<Order> getOrderByUsername(String username);

    // update/create order
    Order saveOrder(Order order);

    // delete order
    String deleteOrderById(int id);
}
