package com.example.coffeerest.service;

import com.example.coffeerest.Entity.User;
import com.example.coffeerest.dto.UserAuth;
import com.example.coffeerest.dto.UserDTO;
import com.example.coffeerest.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User " + username + " Not found");

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        return grantedAuthoritiesList;

    }

    public UserDTO createNewUser(User user) {
        if(user != null) {
            UserDTO userDto=new UserDTO();
            user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
            user=userRepository.save(user);
            BeanUtils.copyProperties(user,userDto);
            return userDto;
        }

        return null;
    }

    public UserDTO login(UserAuth userAuth) {
        if(userAuth!=null){
            User user=userRepository.findByUsername(userAuth.getUsername());
            if(user!=null){
                if(bcryptPasswordEncoder.matches(userAuth.getPassword(), user.getPassword())){
                    UserDTO userDto=new UserDTO();
                    BeanUtils.copyProperties(user,userDto);
                    return userDto;
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
        else {
            return null;
        }
    }
}
