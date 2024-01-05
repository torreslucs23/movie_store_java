package com.example.movies.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "DB_MOVIE")
public class MovieModel  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMovie;
    private String name;
    private String director;
    private int releaseYear;

    public MovieModel(UUID idMovie, String name, String director, int releaseYear, String genre) {
        this.idMovie = idMovie;
        this.name = name;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }
    public MovieModel(){};

    private String genre;


    public UUID getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(UUID idMovie) {
        this.idMovie = idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
