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

    private int year;



    public Movie(long id, String name, String director, int year) {
        this.id = id;
        this.name = name;
        this.director = director;
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
