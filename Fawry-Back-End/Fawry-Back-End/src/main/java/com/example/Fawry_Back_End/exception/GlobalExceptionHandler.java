package com.example.Fawry_Back_End.exception;

import com.example.Fawry_Back_End.constant.ResponseCode;
import org.springframework.security.access.AccessDeniedException;
import com.example.Fawry_Back_End.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        Response errorResponse = new Response(
                errorDetails.get(0));


        return ResponseEntity.ok(errorResponse);
    }
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Response> handleResponseException(
            ResponseException ex, WebRequest request) {
        return ResponseEntity.ok(ex.getResponse());
    }



    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        Response errorResponse = new Response(ResponseCode.Access_Denied);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
