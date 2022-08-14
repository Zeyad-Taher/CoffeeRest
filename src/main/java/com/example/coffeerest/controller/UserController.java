package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    //@Autowired private UserService userService;


    @GetMapping(value = "/profile")
    public User getUser(){
        //TDOD
        return new User("kofta", "koftaiq@mshwyat.com", "Ba2dones123");
    }

    @PutMapping(value = "/edit")
    public User editProfile(@RequestBody User usr){
        //TODO
        return usr;
    }
}
