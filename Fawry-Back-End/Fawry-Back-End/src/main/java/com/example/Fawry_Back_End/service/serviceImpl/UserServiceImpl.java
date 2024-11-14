package com.example.Fawry_Back_End.service.serviceImpl;

import com.example.Fawry_Back_End.constant.AppConstant;
import com.example.Fawry_Back_End.constant.ResponseCode;
import com.example.Fawry_Back_End.dto.request.UserRequest;
import com.example.Fawry_Back_End.dto.response.UserResponse;
import com.example.Fawry_Back_End.exception.ResponseException;
import com.example.Fawry_Back_End.model.Users;
import com.example.Fawry_Back_End.repository.UserRepository;
import com.example.Fawry_Back_End.service.UserService;
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
            throw new ResponseException(ResponseCode.USER_NOT_FOUND);

        return  new User(users.getUsername(), users.getPassword(), Collections.singleton(new SimpleGrantedAuthority(users.getRole())));
    }

    @Override
    public UserResponse save(UserRequest users) {
        Users user = mapperUserRequestToUser(users);
        user = userRepository.save(user);
        if(user == null)
            throw new ResponseException(ResponseCode.ERROR);
        return mapperUserToUserResponse(user);
    }

    private UserResponse mapperUserToUserResponse(Users user) {
        UserResponse response = new UserResponse(ResponseCode.SUCCESS);
        response.setRequest(UserRequest.builder()
                .username(user.getUsername())
                .build());
        return response;
    }

    private Users mapperUserRequestToUser(UserRequest users) {
        String encodedPassword = Base64.getEncoder().encodeToString(users.getPassword().getBytes());
        return Users.builder()
                .username(users.getUsername())
                .password(encodedPassword)
                .role(AppConstant.USER_ROL)
                .build();
    }

}
