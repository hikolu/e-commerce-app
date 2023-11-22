package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.model.User;
import com.hikolu.ecommerceapp.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // retrieve the user from the database
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);

        // place in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // redirect to the home page
        response.sendRedirect(request.getContextPath() + "/");
    }
}
