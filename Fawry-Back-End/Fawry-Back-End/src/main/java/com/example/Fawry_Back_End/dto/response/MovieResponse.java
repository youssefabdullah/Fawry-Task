package com.example.Fawry_Back_End.dto.response;

import com.example.Fawry_Back_End.dto.Response;
import com.example.Fawry_Back_End.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MovieResponse extends Response {
    @JsonProperty(value = "data")
    private List<Movie> movies;
    public MovieResponse (String responseCode){
        super(responseCode);
    }
}
