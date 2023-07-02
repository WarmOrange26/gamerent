package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.UserResponse;
import ru.aston.gamerent.model.entity.User;

public interface UserService {
    UserResponse getUserById(long id);
    User createNewUser();
}