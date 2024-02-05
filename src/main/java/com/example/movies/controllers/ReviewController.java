package com.example.movies.controllers;

import com.example.movies.dtos.ResponseDto;
import com.example.movies.dtos.ReviewDto;
import com.example.movies.models.Review;
import com.example.movies.services.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
@SecurityRequirement(name = "Bearer Authentication")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PreAuthorize("hasRole('default_user')")
    @GetMapping("/{idMovie}/user/{idUser}")
    public ResponseEntity<?> getRatingReview(@PathVariable Long idMovie, @PathVariable Long idUser) {

        Review review = reviewService.findReviewByIdMovieAndUser(idMovie, idUser);

        if (review != null) {
            ReviewDto response = new ReviewDto(idMovie, idUser, review.getRating());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseDto response = new ResponseDto("Error", "review not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('default_user')")
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewDto request){
        Review createdReview = reviewService.createReview(request.movieId(), request.userId(), request.rating());
        if(createdReview != null){
            ReviewDto response = new ReviewDto(request.movieId(), request.userId(), request.rating());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        ResponseDto response = new ResponseDto("Error", "review not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @PreAuthorize("hasRole('default_user')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto request, @PathVariable Long id){
        Review review = reviewService.updateReview(request, id);
        if(review != null){
            ReviewDto response = new ReviewDto(request.movieId(), request.userId(), request.rating());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        ResponseDto response = new ResponseDto("Error", "review not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    @PreAuthorize("hasRole('default_user')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteReview(@PathVariable Long id) {
        boolean deleted = reviewService.deleteReview(id);
        if (deleted){
            ResponseDto response = new ResponseDto("success", "Review deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        else{
            ResponseDto response = new ResponseDto("error", "Review not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
