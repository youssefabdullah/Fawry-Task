package com.example.Fawry_Back_End.controller;

import com.example.Fawry_Back_End.dto.Response;
import com.example.Fawry_Back_End.model.Movie;
import com.example.Fawry_Back_End.service.MovieService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
    @Autowired
    private MovieService service;

    @GetMapping("/getAllMovies")
    public Response findAll(){
        return service.getAllMovies();
    }
}
