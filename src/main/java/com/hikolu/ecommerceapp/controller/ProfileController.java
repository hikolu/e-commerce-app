package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.dto.UserDTOProfile;
import com.hikolu.ecommerceapp.model.User;
import com.hikolu.ecommerceapp.model.UserMapper;
import com.hikolu.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    // expose profile endpoint
    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {

        // get user from principal
        String username = principal.getName();
        User dbUser = userService.getUserByUsername(username);
        UserDTOProfile userDTOProfile = UserMapper.mapToUserDTO(dbUser);

        // add attribute to the model
        model.addAttribute("user", userDTOProfile);

        // return page
        return "profile-main";
    }
}
