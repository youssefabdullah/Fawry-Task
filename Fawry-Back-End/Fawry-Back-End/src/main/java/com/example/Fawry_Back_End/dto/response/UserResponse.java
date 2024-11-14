package com.example.Fawry_Back_End.dto.response;

import com.example.Fawry_Back_End.dto.Response;
import com.example.Fawry_Back_End.dto.request.UserRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends Response {
    @JsonProperty(value = "user")
    private UserRequest request;
    public UserResponse(String responseCode){
        super(responseCode);
    }
}
