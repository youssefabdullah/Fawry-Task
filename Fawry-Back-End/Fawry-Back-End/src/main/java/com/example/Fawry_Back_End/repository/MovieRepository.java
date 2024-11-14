package com.example.Fawry_Back_End.repository;

import com.example.Fawry_Back_End.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
