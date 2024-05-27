package com.example.movies.repositories;

import com.example.movies.models.Movie;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)

class MovieRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Test
    @DisplayName("Should get movie successfully from database")
    void getMovieById() {
        Long id = Long.valueOf(1);
        Movie newMovie = new Movie (0,"superman", "dc", "filme teste", 2008, "");
        newMovie = this.createMovie(newMovie);
        id = newMovie.getId();
        Optional<Movie> result =  this.movieRepository.findById(id);

        assertThat(result.isPresent()).isTrue();
    }

    private Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
