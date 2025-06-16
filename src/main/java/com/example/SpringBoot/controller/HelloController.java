package com.example.SpringBoot.controller;

import com.example.SpringBoot.Services.UserService;
import com.example.SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageAuthenticatedUsers(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return "secured part of web service: " + user.getUsername() + " " + user.getId();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile() {
        return "read profile page";
    }

    @GetMapping("/admin")
    public String pageOnlyForAdmins() {
        return "admin page";
    }

    @GetMapping("/user")
    public String pageOnlyForUsers() {
        return "user page";
    }
}