package com.example.Fawry_Back_End.controller;

import com.example.Fawry_Back_End.constant.ResponseCode;
import com.example.Fawry_Back_End.dto.Response;
import com.example.Fawry_Back_End.dto.request.AuthenticationRequest;
import com.example.Fawry_Back_End.dto.request.UserRequest;
import com.example.Fawry_Back_End.service.AuthService;
import com.example.Fawry_Back_End.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthController {
    @Autowired
    private ResponseCode responseCode;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }

    @PostMapping("/signup")
    public Response saveUser(@Valid @RequestBody UserRequest users) {
        return authService.signup(users);
    }
}
