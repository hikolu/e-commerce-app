package com.hikolu.ecommerceapp.service.impl;

import com.hikolu.ecommerceapp.dao.UserDAO;
import com.hikolu.ecommerceapp.model.User;
import com.hikolu.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public String deleteUserByUsername(String username) {
        return userDAO.deleteUserByUsername(username);
    }
}
