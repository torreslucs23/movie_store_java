package com.example.movies.controllers;

import com.example.movies.config.JwtUtil;
import com.example.movies.dtos.MovieResponseDto;
import com.example.movies.dtos.NewMovieMessage;
import com.example.movies.dtos.ResponseDto;
import com.example.movies.models.Movie;
import com.example.movies.services.MovieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
@SecurityRequirement(name = "Bearer Authentication")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private  SimpMessagingTemplate messagingTemplate;


    public MovieController() {

    }

    @PreAuthorize("hasRole('default_user')")
    @GetMapping
    public Page<MovieResponseDto> getAllMovies(@RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "4") int size,
                                                @RequestParam(required = false) String substring){
        return movieService.getAllMovies(page, size, substring);
    }


    @PreAuthorize("hasRole('default_user')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        MovieResponseDto  movie = movieService.getMovieById(id);
        if(movie == null){
            ResponseDto response = new ResponseDto("error", "Movie not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('default_user')")
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        if(movie != null){
            NewMovieMessage message = new NewMovieMessage("new-movie", createdMovie.getName());
            messagingTemplate.convertAndSend("/notification/movie", message);
            return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
        }
        else{
            ResponseDto response = new ResponseDto("error: Bad Request", "Failed to create the movie. Please check the provided data.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('default_user')")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Movie movie = movieService.updateMovie(id, updatedMovie);
        if(movie != null){
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            ResponseDto response = new ResponseDto("error: Not Found", "Movie not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('default_user')")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if(deleted){
            ResponseDto response = new ResponseDto("success", "Movie deleted successfully");
           return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        else{
            ResponseDto response = new ResponseDto("error? Not Found", "movie doesn't exists");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
}
