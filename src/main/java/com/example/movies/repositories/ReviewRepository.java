package com.example.movies.repositories;

import com.example.movies.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.movie.id = :idMovie AND r.user.id = :idUser")
    Review findByReviewByMovieAndUser(@Param("idMovie") long idMovie,  @Param("idUser") long idUser);


}
