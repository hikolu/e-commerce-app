package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.dto.UserDTOProfile;
import com.hikolu.ecommerceapp.model.ObjectMapper;
import com.hikolu.ecommerceapp.model.Order;
import com.hikolu.ecommerceapp.model.User;
import com.hikolu.ecommerceapp.service.OrderService;
import com.hikolu.ecommerceapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserService userService;
    private OrderService orderService;

    @Autowired
    public ProfileController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    // expose GET "/profile" endpoint
    @GetMapping
    public String getProfile(Model model, Principal principal) {

        // get user from principal
        String username = principal.getName();
        User dbUser = userService.getUserByUsername(username);
        UserDTOProfile userDTOProfile = ObjectMapper.mapToUserDTO(dbUser);

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
    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        // check if it has errors
        if (bindingResult.hasErrors()) {
            return "profile/user-form";
        } else {
            // save user
            userService.saveUser(user);

            // redirect to prevent multiple submissions
            return "redirect:/profile";
        }
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
}
