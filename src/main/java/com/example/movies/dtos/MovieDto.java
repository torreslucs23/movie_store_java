package com.example.movies.dtos;


import org.antlr.v4.runtime.misc.NotNull;

public record MovieDto( String name,  String director,
                              int year) {
}