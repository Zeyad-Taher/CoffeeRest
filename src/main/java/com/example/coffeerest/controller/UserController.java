package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.User;
import com.example.coffeerest.dto.UserAuth;
import com.example.coffeerest.dto.UserDTO;
import com.example.coffeerest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/profile")
    public User getUser(){
        //TDOD
        return new User("kofta","kofta77", "koftaiq@mshwyat.com", "Ba2dones123");
    }

    @PutMapping(value = "/edit")
    public User editProfile(@RequestBody User usr){
        //TODO
        return usr;
    }

    @PostMapping(value = "/new")
    public UserDTO createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PostMapping(value = "/authenticate")
    public UserDTO login(@RequestBody UserAuth userAuth){
        return userService.login(userAuth);
    }
}
