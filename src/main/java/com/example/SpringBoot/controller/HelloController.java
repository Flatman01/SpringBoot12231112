package com.example.SpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/")
    public String homePage() {
        return "hello";
    }

    @GetMapping("/authenticated")
    public String pageAuthenticatedUsers(Principal principal) {
        return "secured part of web service: " + principal.getName();
    }
}