package com.hikolu.ecommerceapp.dao;

import com.hikolu.ecommerceapp.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    User getUserByUsername(String username);

    User saveUser(User user);

    String deleteUserById(int id);
}
