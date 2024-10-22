package com.example.Fawry_Back_End.Service.serviceImpl;

import com.example.Fawry_Back_End.Model.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OmdbApiServiceImpl  {
    private static final String API_KEY = "e88a68e1";
    private static final String OMDB_URL = "http://www.omdbapi.com/";

    @Autowired
    private RestTemplate restTemplate;
    public MovieDTO getMovieByTitle(String title){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(OMDB_URL)
                .queryParam("t", title)
                .queryParam("apikey", API_KEY);

        // Send the GET request and retrieve the response as a MovieDTO object
        return restTemplate.getForObject(builder.toUriString(), MovieDTO.class);
    }
}
