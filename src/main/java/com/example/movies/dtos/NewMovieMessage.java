package com.example.movies.dtos;

public class NewMovieMessage {

    String type;
    String movie;

    public NewMovieMessage(String type, String movie) {
        this.type = type;
        this.movie = movie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
