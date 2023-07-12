package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserResponseDto getUserById(Long id);

    UserDto updateUser(Long id, UserRequestDto userRequestDto);

    Optional<ConfirmationResponseDto> saveUser(RegistrationUserRequestDto registrationUserRequestDto);
  
    Optional<User> findById(Long id);

    boolean confirmEmail(UUID token);
}