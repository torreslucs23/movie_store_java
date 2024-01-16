package com.example.movies.repositories;

import com.example.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Long> {
    @Query("SELECT f FROM Movie f WHERE f.name LIKE %:substring%")
    List<Movie> findMoviesBySubstring(@Param("substring") String substring);

    @Query("SELECT COALESCE(AVG(r.rating), null) FROM Review r WHERE r.movie.id = :movieId")
    Double averageRatingByMovieId(@Param("movieId") Long movieId);

}