package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto getUserById(long id);

    UserDto updateUser(Long id, UserRequestDto userRequestDto);
  
    UserDto saveUser(RegistrationUserRequestDto registrationUserRequestDto);
}