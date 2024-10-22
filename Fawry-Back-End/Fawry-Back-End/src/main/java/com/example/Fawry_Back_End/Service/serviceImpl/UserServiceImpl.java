package com.example.Fawry_Back_End.Service.serviceImpl;

import com.example.Fawry_Back_End.Entity.Users;
import com.example.Fawry_Back_End.Repository.UserRepository;
import com.example.Fawry_Back_End.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails findUserByUserName(String username) {
        Users users = userRepository.findByUsername(username);
        if(users == null)
            return null;
        String encodedPassword = Base64.getEncoder().encodeToString(users.getPassword().getBytes());
        System.out.println(encodedPassword);
        System.out.println(users);
        System.out.println(users.getPassword());
        return  new User(users.getUsername(), users.getPassword(), Collections.singleton(new SimpleGrantedAuthority(users.getRole())));
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }
}
