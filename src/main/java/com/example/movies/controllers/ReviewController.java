package com.example.movies.controllers;

import com.example.movies.dtos.ReviewDto;
import com.example.movies.models.Review;
import com.example.movies.services.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Review> getRatingReview(@PathVariable Long idMovie, @PathVariable Long idUser) {

        Review review = reviewService.findReviewByIdMovieAndUser(idMovie, idUser);

        if (review != null) {

            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('default_user')")
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto request){
        Review createdReview = reviewService.createReview(request.movieId(), request.userId(), request.rating());
        if(createdReview != null){
            return new ResponseEntity<>(createdReview, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PreAuthorize("hasRole('default_user')")
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@RequestBody ReviewDto request, @PathVariable Long id){
        Review review = reviewService.updateReview(request, id);
        if(review != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PreAuthorize("hasRole('default_user')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        boolean deleted = reviewService.deleteReview(id);
        return deleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
