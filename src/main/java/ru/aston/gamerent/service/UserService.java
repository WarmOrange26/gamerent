package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserResponseDto findUserByEmail(String email);

    UserDto updateUser(Long id, UserRequestDto userRequestDto);

    Optional<ConfirmationResponseDto> saveUser(RegistrationUserRequestDto registrationUserRequestDto);

    boolean confirmEmail(UUID token);
}