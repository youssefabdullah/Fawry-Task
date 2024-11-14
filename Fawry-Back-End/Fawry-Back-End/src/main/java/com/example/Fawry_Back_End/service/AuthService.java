package com.example.Fawry_Back_End.service;

import com.example.Fawry_Back_End.dto.request.UserRequest;
import com.example.Fawry_Back_End.model.Users;
import com.example.Fawry_Back_End.dto.request.AuthenticationRequest;
import com.example.Fawry_Back_End.dto.Response;

public interface AuthService {
    Response login(AuthenticationRequest authenticationRequest);
    Response signup(UserRequest users);
}
