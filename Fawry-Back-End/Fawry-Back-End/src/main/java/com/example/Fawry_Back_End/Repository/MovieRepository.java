package com.example.Fawry_Back_End.Repository;

import com.example.Fawry_Back_End.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
