package com.hikolu.ecommerceapp.dao;

import com.hikolu.ecommerceapp.model.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> findRolesByUsername(String username);

    Role save(Role role);
}
