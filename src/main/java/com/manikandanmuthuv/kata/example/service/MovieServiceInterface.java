package com.manikandanmuthuv.kata.example.service;

import com.manikandanmuthuv.kata.example.model.Movie;

public interface MovieServiceInterface {
    public Movie AddMovie(Movie movie);

    public Movie SearchMovie(String byTitle);
    public Movie DeleteMovie(String byTitle);

}