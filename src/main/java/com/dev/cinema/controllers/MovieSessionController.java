package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.session.MovieSessionRequestDto;
import com.dev.cinema.model.dto.session.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper sessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper sessionMapper) {
        this.movieSessionService = movieSessionService;
        this.sessionMapper = sessionMapper;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllSessions(@RequestParam Long id,
                                                        @RequestParam String showTime) {
        return movieSessionService.findAvailableSessions(id, LocalDate.parse(showTime)).stream()
                .map(sessionMapper::toMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(sessionMapper.toMovieSessionEntity(movieSessionRequestDto));
    }
}
