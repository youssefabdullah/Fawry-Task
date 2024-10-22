package com.example.Fawry_Back_End.Service.serviceImpl;

import com.example.Fawry_Back_End.Model.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OmdbApiServiceImpl  {
    private static final String API_KEY = "e88a68e1";
    private static final String OMDB_URL = "https://www.omdbapi.com";

    @Autowired
    private RestTemplate restTemplate;
    public MovieDTO getMovieByTitle(String title){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(OMDB_URL)
                .queryParam("t", title)
                .queryParam("apikey", API_KEY);

        // Set headers to accept JSON response
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Send the GET request and retrieve the response
        ResponseEntity<MovieDTO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                MovieDTO.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get movie details");
        }
    }
}
