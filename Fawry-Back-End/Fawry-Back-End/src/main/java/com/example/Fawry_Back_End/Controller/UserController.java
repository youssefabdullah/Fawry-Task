package com.example.Fawry_Back_End.Controller;

import com.example.Fawry_Back_End.Entity.Movie;
import com.example.Fawry_Back_End.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
    @Autowired
    private MovieService service;

    @GetMapping("/getAllMovies")
    public List<Movie> findAll(){
        return service.getAllMovies();
    }
}
