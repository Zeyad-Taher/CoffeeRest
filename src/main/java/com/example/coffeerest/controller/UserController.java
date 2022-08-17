package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.User;
import com.example.coffeerest.dto.UserAuth;
import com.example.coffeerest.dto.UserDTO;
import com.example.coffeerest.dto.UserName;
import com.example.coffeerest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class for user related endpoints
 */
@RestController
@CrossOrigin
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Endpoint to return the profile of the current user, using the provided JWT header
     *
     * @return returns current user according to the last JWT used
     */
    @GetMapping(value = "/profile")
    public UserDTO getUser() {
        return userService.getCurrentUser();
    }

    /**
     * Endpoint to change the current user's username using the provided JWT header
     *
     * @param user  Serializable object (JSON) that contains name
     * @return ResponseEntity containing either userDto or an Error object indicating a missing parameter (user == null)
     */
    @PutMapping(value = "/edit")
    public ResponseEntity<?> editProfile(@RequestBody(required = false) UserName user) {
        return userService.editProfile(user);
    }

    /**
     * Endpoint to register a new user
     *
     * @param user Serializable object (JSON) that contains name, email and password
     * @return ResponseEntity of type either UserDto or an Error object indicating what went wrong
     */
    @PostMapping(value = "/new")
    public ResponseEntity<?> createNewUser(@RequestBody(required = false) User user) {
        return userService.createNewUser(user);
    }

    /**
     * Endpoint to log in that returns Java Web Token
     *
     * @param userAuth Serializable object (JSON) that contains email and password
     */
    @PostMapping(value = "/login")
    public void login(@RequestBody UserAuth userAuth) {
    }
}
