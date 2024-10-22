package com.example.Fawry_Back_End.Service;

import com.example.Fawry_Back_End.Entity.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    ResponseEntity<String> saveMovie(String title);
    List<Movie> getAllMovies();
    void deleteMovie(int id);
}
