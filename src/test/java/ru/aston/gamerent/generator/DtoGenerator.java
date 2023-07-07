package ru.aston.gamerent.generator;

import lombok.Getter;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.entity.User;

@Getter
public class DtoGenerator {
    private static final EntityGenerator entityGeneratorTest = new EntityGenerator();
    private final User user = entityGeneratorTest.getUser();

    UserResponseDto userResponseDto = UserResponseDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .registrationTime(user.getRegistrationTime())
            .phone(user.getPhone())
            .birthDate(user.getBirthDate())
            .isBlocked(user.getIsBlocked())
            .build();

    UserDto userDto = UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .registrationTime(user.getRegistrationTime())
            .phone(user.getPhone())
            .birthDate(user.getBirthDate())
            .isBlocked(user.getIsBlocked())
            .build();

    UserRequestDto userRequestDto = UserRequestDto.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .phone(user.getPhone())
            .birthDate(user.getBirthDate())
            .build();

    RegistrationUserRequestDto registrationUserRequestDto = RegistrationUserRequestDto.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .phone(user.getPhone())
            .password(user.getPassword())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .birthDate(user.getBirthDate())
            .build();
}
