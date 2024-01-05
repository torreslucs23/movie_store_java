package com.example.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDTO(@NotBlank String name) {

}