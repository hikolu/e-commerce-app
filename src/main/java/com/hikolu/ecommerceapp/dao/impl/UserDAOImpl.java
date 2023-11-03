package com.hikolu.ecommerceapp.dao.impl;

import com.hikolu.ecommerceapp.dao.UserDAO;
import com.hikolu.ecommerceapp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getUsers() {

        // setup query
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);

        // get results
        List<User> results = query.getResultList();

        // return results
        return results;
    }

    @Override
    public User getUserByUsername(String username) {

        // find user
        User dbUser = entityManager.find(User.class, username);

        // return user from db
        return dbUser;
    }

    @Override
    public User saveUser(User user) {

        // save user and retrieve updated/new user
        User dbUser = entityManager.merge(user);

        // return the user from db
        return dbUser;
    }

    @Override
    public String deleteUserById(int id) {

        // find user in db by id
        User dbUser = entityManager.find(User.class, id);

        // delete user
        entityManager.remove(dbUser);

        // return confirmation string
        return "User deleted, id - " + id;
    }
}
