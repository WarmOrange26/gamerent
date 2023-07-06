package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.entity.User;

import java.util.Optional;

public interface UserService {
    UserResponseDto getUserById(long id);
    Optional<User> findById(Long id);

    void updateUser(Long id, UserRequestDto userRequestDto);
  
    boolean saveUser(RegistrationUserRequestDto registrationUserRequestDto);
}