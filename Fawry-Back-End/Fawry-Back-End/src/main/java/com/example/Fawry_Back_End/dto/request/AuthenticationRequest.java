package com.example.Fawry_Back_End.dto.request;

import com.example.Fawry_Back_End.constant.ResponseCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = ResponseCode.USERNAME_INVALID)
    private String username;
    @NotBlank(message = ResponseCode.PASSWORD_INVALID)
    private String password;
}
