package com.hikolu.ecommerceapp.dao;

import com.hikolu.ecommerceapp.model.User;

import java.util.List;

public interface UserDAO {

    // read
    List<User> getUsers();

    User getUserById(int id);

    // create/update
    User saveUser(User user);

    // delete
    String deleteUserById(int id);
}
