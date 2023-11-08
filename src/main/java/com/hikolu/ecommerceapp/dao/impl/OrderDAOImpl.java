package com.hikolu.ecommerceapp.dao.impl;

import com.hikolu.ecommerceapp.dao.OrderDAO;
import com.hikolu.ecommerceapp.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // define constructor
    @Autowired
    public OrderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> getOrders() {

        // define query
        TypedQuery<Order> query = entityManager.createQuery("from Order", Order.class);

        // get results from query
        List<Order> result = query.getResultList();

        // return results
        return result;
    }

    @Override
    public Order getOrderById(int id) {

        // get order from db
        Order order = entityManager.find(Order.class, id);

        // return order
        return order;
    }

    @Override
    public List<Order> getOrderByUsername(String username) {

        // define query
        TypedQuery<Order> query = entityManager.createQuery("from Order u where u.username=:username", Order.class);
        query.setParameter("username", username);

        // get orders from db
        List<Order> orders = query.getResultList();

        // return order
        return orders;
    }

    @Override
    public Order saveOrder(Order order) {

        // save order (if id = 0 -> new else update
        Order dbOrder = entityManager.merge(order);

        // return new/updated employee
        return dbOrder;
    }

    @Override
    public String deleteOrderById(int id) {

        // find order by id
        Order order = entityManager.find(Order.class, id);

        // delete order
        entityManager.remove(order);

        // return deleted id
        return "Deleted Order with id - " + order.getOrderId();
    }
}
