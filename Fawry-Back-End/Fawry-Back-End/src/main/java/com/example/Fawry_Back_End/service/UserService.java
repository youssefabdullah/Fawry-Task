package com.example.Fawry_Back_End.service;

import com.example.Fawry_Back_End.dto.request.AuthenticationRequest;
import com.example.Fawry_Back_End.dto.request.UserRequest;
import com.example.Fawry_Back_End.dto.response.UserResponse;
import com.example.Fawry_Back_End.model.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails findUserByUserName(String authenticationRequest);
    UserResponse save (UserRequest users);
}
