package com.example.Fawry_Back_End.controller;

import com.example.Fawry_Back_End.dto.Response;
import com.example.Fawry_Back_End.service.serviceImpl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {
    @Autowired
    private MovieServiceImpl service;
    @PostMapping("/saveMovie/{title}")
    public ResponseEntity<Response> addMovie(@PathVariable("title") String title) {
        service.saveMovie(title);
            return  ResponseEntity.ok(new Response("200"));
    }
    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<Response> deleteMovie(@PathVariable("id") int id){
        service.deleteMovie(id);
        return  ResponseEntity.ok(new Response("200"));
    }
}
