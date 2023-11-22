package com.hikolu.ecommerceapp.dao.impl;

import com.hikolu.ecommerceapp.dao.RoleDAO;
import com.hikolu.ecommerceapp.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> findRolesByUsername(String username) {

        // setup query
        TypedQuery<Role> query = entityManager.createQuery("from Role where username=:user", Role.class);
        query.setParameter("user", username);

        List<Role> roles = query.getResultList();

        return roles;
    }
}
