package com.example.Fawry_Back_End.dto;

import com.example.Fawry_Back_End.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response {
    private String responseCode;
    private String message;
    public Response(String responseCode){
        this.responseCode = responseCode;
        this.message = ResponseCode.map.get(responseCode);
    }
}
