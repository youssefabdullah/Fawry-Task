package com.example.Fawry_Back_End.Service;

import com.example.Fawry_Back_End.Entity.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails findUserByUserName(String username);
    Users save (Users users);
}
