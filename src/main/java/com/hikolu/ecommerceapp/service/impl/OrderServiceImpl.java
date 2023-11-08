package com.hikolu.ecommerceapp.service.impl;

import com.hikolu.ecommerceapp.dao.OrderDAO;
import com.hikolu.ecommerceapp.model.Order;
import com.hikolu.ecommerceapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public List<Order> getOrderByUsername(String username) {
        return orderDAO.getOrderByUsername(username);
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderDAO.saveOrder(order);
    }

    @Override
    @Transactional
    public String deleteOrderById(int id) {
        return orderDAO.deleteOrderById(id);
    }
}
