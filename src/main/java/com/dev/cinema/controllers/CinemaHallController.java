package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.hall.CinemaHallRequestDto;
import com.dev.cinema.model.dto.hall.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.mapper.HallMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final HallMapper hallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                HallMapper hallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.hallMapper = hallMapper;
    }

    @PostMapping
    public void create(@RequestBody @Valid CinemaHallRequestDto cinemaHallDto) {
        cinemaHallService.add(hallMapper.convertToEntity(cinemaHallDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(hallMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
