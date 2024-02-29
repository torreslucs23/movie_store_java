package com.example.movies.mappers;

import com.example.movies.dtos.MovieResponseDto;
import com.example.movies.models.Movie;
import com.example.movies.repositories.MovieRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")

public interface MovieMapper {

@Mapping(target = "meanRating", expression = "java(movieRepository.averageRatingByMovieId(movie.getId()))")
    MovieResponseDto toMovieResponseDto(Movie movie, MovieRepository movieRepository);
}
