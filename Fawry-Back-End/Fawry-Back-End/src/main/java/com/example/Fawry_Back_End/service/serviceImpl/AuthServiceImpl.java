package com.example.Fawry_Back_End.service.serviceImpl;

import com.example.Fawry_Back_End.config.JwtUtile;
import com.example.Fawry_Back_End.constant.ResponseCode;
import com.example.Fawry_Back_End.dto.Response;
import com.example.Fawry_Back_End.dto.request.AuthenticationRequest;
import com.example.Fawry_Back_End.dto.request.UserRequest;
import com.example.Fawry_Back_End.dto.response.AuthenticationResponse;
import com.example.Fawry_Back_End.exception.ResponseException;
import com.example.Fawry_Back_End.model.Users;
import com.example.Fawry_Back_End.repository.UserRepository;
import com.example.Fawry_Back_End.service.AuthService;
import com.example.Fawry_Back_End.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtile jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Response login(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest.getUsername().isEmpty())
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        UserDetails user = userService.findUserByUserName(authenticationRequest.getUsername());

        String encodedPassword = Base64.getEncoder().encodeToString(authenticationRequest.getPassword().getBytes());

        if (!user.getPassword().equals(encodedPassword))
            throw new ResponseException(ResponseCode.PASSWORD_INVALID);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(ResponseCode.SUCCESS);

        authenticationResponse.setRole(String.valueOf(user.getAuthorities().stream().findAny().get()));
        authenticationResponse.setAccessToken(jwtUtil.generateToken(user));

        return authenticationResponse;
    }

    @Override
    public Response signup(UserRequest users) {
        Users user = userRepository.findByUsername(users.getUsername());
        if(user != null)
            throw new ResponseException(ResponseCode.USER_ALREADY_EXIST);
        return userService.save(users);
    }
}
