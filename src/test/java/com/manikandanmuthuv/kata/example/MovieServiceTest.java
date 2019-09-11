package com.manikandanmuthuv.kata.example;

import java.util.UUID;

import com.manikandanmuthuv.kata.example.model.Movie;
import com.manikandanmuthuv.kata.example.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;


public class MovieServiceTest {
 
    MovieService movieService = new MovieService();

    @Test
    public void AddMovie() {
        Movie actualMovie = Movie.builder().id(UUID.randomUUID().toString()).title("Avengers EndGame").genre("Fantasy")
                .build();
        Movie expectedMovie = movieService.AddMovie(actualMovie);
        assertThat(actualMovie.getTitle()).isEqualTo(expectedMovie.getTitle());
    }

    @Test
    public void SearchMovie() {
        Movie movie = Movie.builder().id(UUID.randomUUID().toString()).title("Avengers EndGame").genre("Fantasy")
                .build();
        Movie actualMovie = movieService.AddMovie(movie);
        Movie expectedMovie = movieService.SearchMovie(actualMovie.getTitle());
        assertThat(actualMovie.getTitle()).isEqualTo(expectedMovie.getTitle());
    }

    @Test
    public void DeleteMovie() {
        Movie movie = Movie.builder().id(UUID.randomUUID().toString()).title("Avengers EndGame").genre("Fantasy")
                .build();
        movieService.AddMovie(movie);
        movieService.DeleteMovie(movie.getTitle());
        Movie expectedMovie = movieService.SearchMovie(movie.getTitle());

        assertThat(expectedMovie).isNull();
    }
}