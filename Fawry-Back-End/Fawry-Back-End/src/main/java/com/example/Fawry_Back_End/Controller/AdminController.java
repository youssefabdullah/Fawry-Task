package com.example.Fawry_Back_End.Controller;

import com.example.Fawry_Back_End.Entity.Movie;
import com.example.Fawry_Back_End.Service.serviceImpl.MovieServiceImpl;
import com.example.Fawry_Back_End.Service.serviceImpl.OmdbApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private MovieServiceImpl service;
    @PostMapping("/saveMovie/{title}")
    public ResponseEntity<String> addMovie(@PathVariable("title") String title) {
            return service.saveMovie(title);
    }
    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") int id){
        service.deleteMovie(id);
        return ResponseEntity.ok("Succesful delete movie");
    }
}
