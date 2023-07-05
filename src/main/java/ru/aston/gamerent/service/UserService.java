package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.UserRequest;
import ru.aston.gamerent.model.dto.RegistrationUser;
import ru.aston.gamerent.model.dto.response.UserResponse;

public interface UserService {
    UserResponse getUserById(long id);

    void updateUser(Long id, UserRequest userRequest);
  
    boolean saveUser(RegistrationUser registrationUser);
}