package com.example.Fawry_Back_End.Controller;

import com.example.Fawry_Back_End.Entity.Users;
import com.example.Fawry_Back_End.Model.AuthenticationRequest;
import com.example.Fawry_Back_End.Model.AuthenticationResponse;
import com.example.Fawry_Back_End.Service.UserService;
import com.example.Fawry_Back_End.config.JwtUtile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtile jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        if(authenticationRequest.getUsername().isEmpty() )
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        UserDetails user = userService.findUserByUserName(authenticationRequest.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (user != null) {

            authenticationResponse.setResponseCode(200);
            authenticationResponse.setAccessToken(jwtUtil.generateToken(user));
            authenticationResponse.setMessage("You are login");
            return ResponseEntity.ok(authenticationResponse);
        }
        authenticationResponse.setResponseCode(300);
        authenticationResponse.setAccessToken("none");
        authenticationResponse.setMessage("Not found user");
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/signup")
    public Users saveUser(@RequestBody Users users) {
        return userService.save(users);
    }
}
