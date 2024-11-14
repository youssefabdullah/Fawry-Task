package com.example.Fawry_Back_End.dto.request;

import com.example.Fawry_Back_End.constant.ResponseCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequest {
    @NotBlank(message = ResponseCode.USERNAME_INVALID)
    private String username;
    @NotBlank(message = ResponseCode.PASSWORD_INVALID)
    @Size(min = 8, max = 20, message = ResponseCode.PASSWORD_INVALID_Length)
    private String password;
}
