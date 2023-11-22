package com.hikolu.ecommerceapp.service.impl;

import com.hikolu.ecommerceapp.dao.RoleDAO;
import com.hikolu.ecommerceapp.dao.UserDAO;
import com.hikolu.ecommerceapp.model.Role;
import com.hikolu.ecommerceapp.model.User;
import com.hikolu.ecommerceapp.model.WebUser;
import com.hikolu.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
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
    public User saveUser(WebUser webUser) {

        User user = new User();

        // assign user details to the user object
        user.setUsername(webUser.getUsername());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setEnabled(1);
        user.setEmail(webUser.getEmail());

        User dbUser = userDAO.saveUser(user);

        // give user default role
        Role userRole = new Role(webUser.getUsername(), "ROLE_USER");
        roleDAO.save(userRole);

        // save user
        return dbUser;
    }

    @Override
    @Transactional
    public String deleteUserByUsername(String username) {
        return userDAO.deleteUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(roleDAO.findRolesByUsername(username)));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }
}
