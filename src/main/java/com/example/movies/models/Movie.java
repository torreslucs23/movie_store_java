package com.example.movies.models;

import jakarta.persistence.*;

@Entity
@Table(name="movies")

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String director;

    private String description;

    private int year;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Movie(long id, String name, String director, String description, int year) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.description = description;
        this.year = year;
    }
    public Movie(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
