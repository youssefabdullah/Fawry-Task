package com.example.Fawry_Back_End.dto.response;

import com.example.Fawry_Back_End.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class AuthenticationResponse extends Response {
    private String accessToken;
    private String role;

    public AuthenticationResponse(String responseCode) {
        super(responseCode);
    }
}
