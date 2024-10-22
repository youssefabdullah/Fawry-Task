package com.example.Fawry_Back_End.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class AuthenticationResponse extends Response{
    private String accessToken;
    private String role;
}
