package com.example.coffeerest.service;

import com.example.coffeerest.Entity.User;
import com.example.coffeerest.dto.UserDTO;
import com.example.coffeerest.dto.UserName;
import com.example.coffeerest.exception.ErrorResponse;
import com.example.coffeerest.exception.Errors;
import com.example.coffeerest.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for UserController mainly used to provide authentication business logic
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    /**
     * Finds user by username
     *
     * @param email String containing the email of the user to be found
     * @return User     Object containing the email and password of the requested user
     * @throws UsernameNotFoundException If user not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("Email " + email + " Not found");

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities());
    }

    /**
     * Function that provides empty authorities list
     *
     * @return an empty ArrayList<GrantedAuthrity>
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        return grantedAuthoritiesList;

    }

    /**
     * Provides business logic for signing up a new user
     *
     * @param user User Entity which is to be added
     * @return ResponseEntity of type either UserDto or an Error object indicating what went wrong
     */
    public ResponseEntity<?> createNewUser(User user) {
        if (user != null) {
            if (!userRepository.existsByEmail(user.getEmail())) {
                UserDTO userDto = new UserDTO();
                user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
                user = userRepository.save(user);
                BeanUtils.copyProperties(user, userDto);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ErrorResponse(Errors.USER_EMAIL_ALREADY_EXIST.getCode(),
                        Errors.USER_EMAIL_ALREADY_EXIST.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ErrorResponse(Errors.USER_IS_MISSING.getCode(),
                    Errors.USER_IS_MISSING.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @return returns current user according to the last JWT used
     */
    public UserDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginUsername = (String) auth.getPrincipal();
        User user = userRepository.findUserByEmail(loginUsername);
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    /**
     * Busniness logic for updating profile
     *
     * @param user UserName object that contains the new username of current user
     * @return ResponseEntity containing either userDto or an Error object indicating a missing parameter (user == null)
     */
    public ResponseEntity<?> editProfile(UserName user) {
        if (user != null) {
            Long userId = getCurrentUser().getId();
            User newUser = userRepository.findById(userId).get();
            newUser.setName(user.getName());
            userRepository.save(newUser);
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(newUser, userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(Errors.USER_IS_MISSING.getCode(),
                    Errors.USER_IS_MISSING.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
