package com.example.movies.controllers;

import com.example.movies.dtos.MovieRecordDTO;
import com.example.movies.models.MovieModel;
import com.example.movies.repositories.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MovieController {
    @Autowired
    MovieRepository movieRepository;

    @PostMapping(value="/movies")
    public ResponseEntity<MovieModel> saveMovie(@RequestBody @Valid MovieRecordDTO movieRecordDTO){
        var movieModel = new MovieModel();
        BeanUtils.copyProperties(movieRecordDTO, movieModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieRepository.save(movieModel));
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> getAllMovies(){
        return ResponseEntity.status(HttpStatus.OK).body(movieRepository.findAll());
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<?> getOneMovie(@PathVariable(value = "id") UUID id){
        Optional<MovieModel> movie0 = movieRepository.findById(id);
        if(movie0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movie0.get());

    }
    @PutMapping(value = "/movies/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable(value="id") UUID id,
                                         @RequestBody @Valid MovieRecordDTO movieRecordDTO){
        Optional<MovieModel> movie0 = movieRepository.findById(id);
        if(movie0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("Movie not found"));
        }
        var movieModel = movie0.get();
        BeanUtils.copyProperties(movieRecordDTO, movieModel);
        return ResponseEntity.status(HttpStatus.OK).body(movieRepository.save(movieModel));
    }

    @DeleteMapping(value="/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value="id") UUID id){
        Optional<MovieModel> movie0 = movieRepository.findById(id);
        if(movie0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("movie not found");
        }
        movieRepository.delete(movie0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }


}
