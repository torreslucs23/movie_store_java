package com.example.movies.repositories;

import com.example.movies.models.Movie;
import com.example.movies.models.Review;
import com.example.movies.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.movie.id = :idMovie AND r.user.id = :idUser")
    Review findByReviewByMovieAndUser(@Param("idMovie") long idMovie,  @Param("idUser") long idUser);
    boolean existsByUserAndMovie(User user, Movie movie);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.movie.id = :movieId")
    void deleteMovieReviews(@Param("movieId") Long movieId);


}
