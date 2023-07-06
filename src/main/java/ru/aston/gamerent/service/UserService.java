package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto getUserById(long id);

    void updateUser(Long id, UserRequestDto userRequestDto);
  
    boolean saveUser(RegistrationUserRequestDto registrationUserRequestDto);
}