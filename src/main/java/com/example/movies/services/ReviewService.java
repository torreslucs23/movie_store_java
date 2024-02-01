package com.example.movies.services;

import com.example.movies.dtos.ReviewDto;
import com.example.movies.models.Movie;
import com.example.movies.models.Review;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.repositories.ReviewRepository;
import com.example.movies.user.User;
import com.example.movies.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private  MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review findReviewByIdMovieAndUser(Long idMovie, Long idUser){
        return reviewRepository.findByReviewByMovieAndUser(idMovie, idUser);
    }

    public Review createReview(Long idMovie, Long idUser, int rating) {
        Movie movie = movieRepository.findById(idMovie).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);

        if (movie == null || user == null) {
            return null;
        }

        if (reviewRepository.existsByUserAndMovie(user, movie)) {

            return null;
        }

        Review review = new Review(user, movie, rating);
        return reviewRepository.save(review);
    }

    public Review updateReview(ReviewDto reviewDto, Long id) {
        Review existingReview = reviewRepository.findById(id).orElse(null);

        if (existingReview == null) {
            return null;
        }

        Movie movie = movieRepository.findById(reviewDto.movieId()).orElse(null);
        User user = userRepository.findById(reviewDto.userId()).orElse(null);

        if (movie == null || user == null) {
            return null;
        }

        if (reviewRepository.existsByUserAndMovie(user, movie) && !user.equals(existingReview.getUser()) && !movie.equals(existingReview.getMovie())) {
            return null;
        }

        existingReview.setUser(user);
        existingReview.setMovie(movie);
        existingReview.setRating(reviewDto.rating());

        return reviewRepository.save(existingReview);

    }

    public boolean deleteReview(Long id){
        if(reviewRepository.existsById(id)){
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


