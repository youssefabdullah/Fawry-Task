package com.example.Fawry_Back_End.repository;

import com.example.Fawry_Back_End.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
