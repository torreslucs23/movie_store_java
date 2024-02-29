package com.example.movies.dtos;

public record MovieResponseDto(Long id, String name, String director, String description, int year, Double meanRating, String imgUrl) {

}
