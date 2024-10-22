package com.example.Fawry_Back_End.Controller;

import com.example.Fawry_Back_End.Entity.Movie;
import com.example.Fawry_Back_End.Model.Response;
import com.example.Fawry_Back_End.Service.serviceImpl.MovieServiceImpl;
import com.example.Fawry_Back_End.Service.serviceImpl.OmdbApiServiceImpl;
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
            return  ResponseEntity.ok(new Response(200,"Add Succeful"));
    }
    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<Response> deleteMovie(@PathVariable("id") int id){
        service.deleteMovie(id);
        return  ResponseEntity.ok(new Response(200,"Delete Succeful"));
    }
}
