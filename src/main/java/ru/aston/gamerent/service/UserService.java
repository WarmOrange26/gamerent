package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.RegistrationUser;
import ru.aston.gamerent.model.dto.response.UserResponse;

public interface UserService {

    UserResponse getUserById(long id);

    boolean saveUser(RegistrationUser registrationUser);
}