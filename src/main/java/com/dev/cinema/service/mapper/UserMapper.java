package com.dev.cinema.service.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.user.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto convertToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setId(user.getId());
        return userResponseDto;
    }
}
