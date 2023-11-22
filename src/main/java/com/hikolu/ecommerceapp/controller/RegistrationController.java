package com.hikolu.ecommerceapp.controller;

import com.hikolu.ecommerceapp.model.User;
import com.hikolu.ecommerceapp.model.WebUser;
import com.hikolu.ecommerceapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class RegistrationController {

    private UserService userService;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("webUser", new WebUser());

        return "registration/registration-form";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@Valid @ModelAttribute("webUser") WebUser webUser,
                                          BindingResult bindingResult,
                                          HttpSession session, Model model) {

        // get username
        String username = webUser.getUsername();
        logger.info("Processing registration form for: " + username);

        // form validation
        if (bindingResult.hasErrors()) {
            return "registration/registration-form";
        }

        // check if already exists
        User existing = userService.getUserByUsername(username);
        if (existing != null) {

            model.addAttribute("webUser", new WebUser());
            model.addAttribute("registrationError", "Username already exists.");
            logger.warning("User name already exists.");
            return "registration/registration-form";
        }

        // create user account and store in the database
        userService.saveUser(webUser);
        logger.info("Successfully created user: " + username);

        // put in the session
        session.setAttribute("user", webUser);

        return "registration/registration-confirmation";
    }
}
