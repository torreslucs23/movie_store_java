package com.example.movies.services;

import com.example.movies.dtos.MovieResponseDto;
import com.example.movies.models.Movie;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private  ReviewRepository reviewRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponseDto> movieResponseDtos = new ArrayList<>();

        for (Movie movie : movies) {
            MovieResponseDto movieDto = new MovieResponseDto(movie.getId(),movie.getName(), movie.getDirector(),
                    movie.getDescription(), movie.getYear(), movieRepository.averageRatingByMovieId(movie.getId()), movie.getImgUrl());
            movieResponseDtos.add(movieDto);
        }

        return movieResponseDtos;
    }

    public MovieResponseDto getMovieById(Long id) {
        Optional<Movie> optMovie = movieRepository.findById(id);
        if(optMovie.isPresent()){
            Movie movie = optMovie.get();
            MovieResponseDto movieDto = new MovieResponseDto(movie.getId(),movie.getName(), movie.getDirector(),
                    movie.getDescription(), movie.getYear(),
                    movieRepository.averageRatingByMovieId(movie.getId()), movie.getImgUrl());
            return movieDto;
        }
        return null;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        if (movieRepository.existsById(id)) {
            updatedMovie.setId(id);
            return movieRepository.save(updatedMovie);
        }
        return null;
    }

    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            reviewRepository.deleteMovieReviews(id);
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<MovieResponseDto> findMovieBySubstring(String substring){
        List<Movie> movies = movieRepository.findMoviesBySubstring(substring);

        List<MovieResponseDto> movieResponseDtos = new ArrayList<>();

        for (Movie movie : movies) {
            MovieResponseDto movieDto = new MovieResponseDto(movie.getId(),movie.getName(), movie.getDirector(),
                    movie.getDescription(), movie.getYear(),
                    movieRepository.averageRatingByMovieId(movie.getId()), movie.getImgUrl());
            movieResponseDtos.add(movieDto);
        }

        return movieResponseDtos;
    }
}