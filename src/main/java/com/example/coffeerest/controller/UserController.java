package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.User;
import com.example.coffeerest.dto.UserAuth;
import com.example.coffeerest.dto.UserDTO;
import com.example.coffeerest.dto.UserName;
import com.example.coffeerest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/profile")
    public UserDTO getUser(){
        return userService.getCurrentUser();
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<?> editProfile(@RequestBody(required=false) UserName user){
        return userService.editProfile(user);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> createNewUser(@RequestBody(required=false) User user) {
        return userService.createNewUser(user);
    }

    @PostMapping(value = "/authenticate")
    public UserDTO login(@RequestBody UserAuth userAuth){
        return userService.login(userAuth);
    }
}
