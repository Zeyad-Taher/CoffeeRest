package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/profile")
    public User getUser(){
        return
    }
}
