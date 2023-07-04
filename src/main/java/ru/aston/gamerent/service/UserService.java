package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.UserResponse;

public interface UserService {
    UserResponse getUserById(long id);
}