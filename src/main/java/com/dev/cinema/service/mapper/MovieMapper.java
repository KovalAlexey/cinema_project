package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private final MovieService movieService;

    public MovieMapper(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieResponseDto toMovieDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }

    public Movie toMovieEntity(MovieRequestDto movieDto) {
        Movie movie = new Movie();
        movie.setDescription(movieDto.getDescription());
        movie.setTitle(movieDto.getTitle());
        return movie;
    }
}
