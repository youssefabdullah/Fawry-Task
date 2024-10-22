package com.example.Fawry_Back_End.Repository;

import com.example.Fawry_Back_End.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
