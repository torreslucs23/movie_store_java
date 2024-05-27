package com.example.movies.repositories;

import com.example.movies.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Long>, JpaSpecificationExecutor<Movie> {


    @Query("SELECT COALESCE(AVG(r.rating), null) FROM Review r WHERE r.movie.id = :movieId")
    Double averageRatingByMovieId(@Param("movieId") Long movieId);



}