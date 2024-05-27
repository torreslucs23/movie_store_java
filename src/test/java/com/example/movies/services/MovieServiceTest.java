package com.example.movies.services;

import com.example.movies.models.Movie;
import com.example.movies.repositories.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MovieServiceTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("should display all movies in the library")
    void getAllMovies() {

    }

    @Test
    void add(){
        MovieService movieService = new MovieService(movieRepository);
        int n = movieService.add(2,3);
        assertEquals(5, n);
    }
}