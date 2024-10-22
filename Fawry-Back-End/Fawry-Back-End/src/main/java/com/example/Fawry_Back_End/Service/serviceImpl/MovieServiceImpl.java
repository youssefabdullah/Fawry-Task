package com.example.Fawry_Back_End.Service.serviceImpl;

import com.example.Fawry_Back_End.Entity.Movie;
import com.example.Fawry_Back_End.Model.MovieDTO;
import com.example.Fawry_Back_End.Repository.MovieRepository;
import com.example.Fawry_Back_End.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private OmdbApiServiceImpl service;
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public ResponseEntity<String> saveMovie(String title) {
        MovieDTO movieDTO = service.getMovieByTitle(title);
        if (movieDTO != null) {
            Movie movie = mapperMovieDtoToMovie(movieDTO);
            movieRepository.save(movie);
            return ResponseEntity.ok("Movie added successfully");
        }
        return ResponseEntity.badRequest().body("Movie not found in OMDB");
    }
    protected Movie mapperMovieDtoToMovie(MovieDTO dto){
        return Movie.builder()
                .title(dto.getTitle())
                .imdbID(dto.getImdbID())
                .poster(dto.getPoster())
                .country(dto.getCountry())
                .language(dto.getLanguage())
                .plot(dto.getPlot())
                .imdbRating(dto.getImdbRating())
                .build();
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public void deleteMovie(int id){
        Optional<Movie> movie = movieRepository.findById(id);
        movieRepository.deleteById(id);
    }
}
