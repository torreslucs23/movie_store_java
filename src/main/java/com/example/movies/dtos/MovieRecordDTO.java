package com.example.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieRecordDTO(@NotBlank String name, @NotBlank String director,
                             @NotNull int releaseYear, @NotBlank String genre) {

}
