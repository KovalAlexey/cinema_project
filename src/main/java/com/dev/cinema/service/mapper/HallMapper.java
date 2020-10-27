package com.dev.cinema.service.mapper;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.hall.CinemaHallRequestDto;
import com.dev.cinema.model.dto.hall.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HallMapper {
    public CinemaHallResponseDto convertToResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        return cinemaHallResponseDto;
    }

    public CinemaHall convertToEntity(CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(requestDto.getCapacity());
        cinemaHall.setDescription(requestDto.getDescription());
        return cinemaHall;
    }
}
