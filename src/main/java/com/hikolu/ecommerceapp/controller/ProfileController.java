package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.dto.UserDTOProfile;
import com.hikolu.ecommerceapp.model.Order;
import com.hikolu.ecommerceapp.model.User;
import com.hikolu.ecommerceapp.model.UserMapper;
import com.hikolu.ecommerceapp.service.OrderService;
import com.hikolu.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private OrderService orderService;

    @Autowired
    public ProfileController(UserService userService, PasswordEncoder passwordEncoder, OrderService orderService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
    }

    // expose GET "/profile" endpoint
    @GetMapping
    public String getProfile(Model model, Principal principal) {

        // get user from principal
        String username = principal.getName();
        User dbUser = userService.getUserByUsername(username);
        UserDTOProfile userDTOProfile = UserMapper.mapToUserDTO(dbUser);

        // add attribute to the model
        model.addAttribute("user", userDTOProfile);

        // return page
        return "profile/profile-main";
    }

    // expose GET "/user-form" to update profile
    @GetMapping("/user-form")
    public String showUpdateForm(Principal principal, Model model) {

        // get user from db
        User dbUser = userService.getUserByUsername(principal.getName());

        // add attribute to the model to prepopulate form
        model.addAttribute("user", dbUser);

        // return page
        return "profile/user-form";
    }

    // expose POST "/save" to save profile
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);

        // save user
        userService.saveUser(user);

        // redirect to prevent multiple submissions
        return "redirect:/profile";
    }

    // expose GET "/my-orders" endpoint
    @GetMapping("/my-orders")
    public String getOrdersByUser(Model model, Principal principal) {

        // get orders specific to the current user
        List<Order> orders = orderService.getOrderByUsername(principal.getName());

        // add attributes to the model
        model.addAttribute("orders", orders);

        // return page
        return "/profile/my-orders";
    }

    // expose GET "/delete"
    @GetMapping("/delete")
    public String deleteProfile(@RequestParam("user_id") int userId) {

        // delete the user
        userService.deleteUserById(userId);

        // redirect
        return "redirect:/store";
    }
}
