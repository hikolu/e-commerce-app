package com.hikolu.ecommerceapp.service;

import com.hikolu.ecommerceapp.model.User;

import java.util.List;

public interface UserService{

    // get all users
    List<User> getUsers();

    // get single user by username
    User getUserByUsername(String username);

    // update/create user
    User saveUser(User user);

    // delete user
    String deleteUserByUsername(String username);
}
