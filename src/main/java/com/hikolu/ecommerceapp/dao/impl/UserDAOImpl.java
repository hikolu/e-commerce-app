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

    // define field for entity manager
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getUsers() {

        // define query
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);

        // get results
        List<User> result = query.getResultList();

        // return results from db
        return result;
    }

    @Override
    public User getUserById(int id) {

        // find user in db
        User user = entityManager.find(User.class, id);

        // return the user from db
        return user;
    }

    @Override
    public User saveUser(User user) {

        // save user into db and retrieve updated user
        User dbUser = entityManager.merge(user);

        // return the updated user
        return dbUser;
    }

    @Override
    public String deleteUserById(int id) {

        // find user in db
        User dbUser = entityManager.find(User.class, id);

        // delete user
        entityManager.remove(dbUser);

        // return confirmation string
        return "Deleted user with id - " + id;
    }
}
