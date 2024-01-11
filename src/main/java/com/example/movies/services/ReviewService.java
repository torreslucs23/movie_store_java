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

    public Review createReview(Long idMovie, Long idUser, int rating){
        Movie movie = movieRepository.findById(idMovie).orElse(null);
        if (movie == null){
            return null;

        }
        User user = userRepository.findById(idUser).orElse(null);
        if (user == null){
            return null;
        }

        Review review = new Review(user, movie, rating);
        return reviewRepository.save(review);
    }

    public Review updateReview(ReviewDto review, Long id){
        Review existsReview = reviewRepository.findById(id).orElse(null);
        if (existsReview == null){
            return null;
        }

        Movie movie = movieRepository.findById(review.movieId()).orElse(null);
        if (movie == null){
            return null;
        }
        User user = userRepository.findById(review.userId()).orElse(null);
        if (user == null){
            return null;
        }
        Review newReview = new Review(user, movie, review.rating());
        newReview.setId(id);
        return newReview;

    }

    public boolean deleteReview(Long id){
        if(reviewRepository.existsById(id)){
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


