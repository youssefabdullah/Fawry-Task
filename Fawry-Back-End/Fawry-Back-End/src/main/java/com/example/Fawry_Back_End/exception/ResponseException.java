package com.example.Fawry_Back_End.exception;

import com.example.Fawry_Back_End.dto.Response;
import lombok.Getter;

@Getter
public class ResponseException extends RuntimeException {
    private Response response;

    public ResponseException(String responseCode) {
        this.response = new Response(responseCode);
    }

}
