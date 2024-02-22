package com.example.movies.services;

import com.example.movies.dtos.MovieResponseDto;
import com.example.movies.mappers.MovieMapper;
import com.example.movies.models.Movie;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.repositories.ReviewRepository;
import com.example.movies.specifications.MovieSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    private MovieMapper movieMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<MovieResponseDto> getAllMovies(int page, int size, String substring) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Movie> movies = movieRepository.findAll(Specification.where(MovieSpecifications.nameContains(substring) ), pageable);

        return movies.map(movie-> movieMapper.toMovieResponseDto(movie, movieRepository)) ;
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

}