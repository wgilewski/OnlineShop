package com.app.model.dto.mappers;

import com.app.model.dto.UserDto;
import com.app.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class ConverterDto {


    public User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .isActive(userDto.isActive())
                .role(userDto.getRole())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .isActive(user.isActive())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }


}
