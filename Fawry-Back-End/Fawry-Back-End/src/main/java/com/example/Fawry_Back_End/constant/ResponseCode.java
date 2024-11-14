package com.example.Fawry_Back_End.constant;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseCode {

    public static final String SUCCESS = "200";
    public static final String CREATE_USER = "201";

    public static final String USERNAME_INVALID = "300";
    public static final String PASSWORD_INVALID = "301";
    public static final String USER_NOT_FOUND = "302";
    public static final String USER_ALREADY_EXIST = "303";
    public static final String PASSWORD_INVALID_Length = "304";

    public static final String Access_Denied = "403";
    public static final String ERROR = "500";

    public static final Map<String, String> map = new HashMap<>();

    private ResponseCode() {
        map.put(SUCCESS, "The operation was completed successfully.");
        map.put(CREATE_USER, "You created user successfully.");
        // Start 300
        map.put(USERNAME_INVALID, "User name is invalid.");
        map.put(PASSWORD_INVALID, "password is invalid.");
        map.put(USER_NOT_FOUND, "user not found.");
        map.put(USER_ALREADY_EXIST, "user already exists.");
        map.put(PASSWORD_INVALID_Length, "Password must be between 8 and 20 characters.");

        // End 300

        // Start 400
        map.put(Access_Denied, "Access denied: You do not have permission to access this resource.");
        // End 400

        // Start 500
        map.put(ERROR, "A system error has occurred.");
        // End 500
    }
}
