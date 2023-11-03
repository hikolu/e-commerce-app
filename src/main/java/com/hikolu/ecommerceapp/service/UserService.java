package com.hikolu.ecommerceapp.service;

import com.hikolu.ecommerceapp.model.User;

import java.util.List;

public interface UserService {

    // get all users
    List<User> getUsers();

    // get single user by id
    User getUserById(int id);

    // update/create user
    User saveUser(User user);

    // delete user
    String deleteUserById(int id);
}
