package com.example.Fawry_Back_End.service;

import com.example.Fawry_Back_End.dto.response.MovieResponse;
import com.example.Fawry_Back_End.model.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    ResponseEntity<String> saveMovie(String title);
    MovieResponse getAllMovies();
    void deleteMovie(int id);
}
