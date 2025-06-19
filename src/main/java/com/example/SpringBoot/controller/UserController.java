package com.example.SpringBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/profile")
    public String userProfile(Model model, Principal principal) {
        logger.info("User profile requested by " + (principal != null ? principal.getName() : "anonymous"));
        model.addAttribute("message", "User profile page");
        return "user/profile";
    }
}
