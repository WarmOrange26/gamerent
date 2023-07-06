package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.security.AuthenticationRequestDto;
import ru.aston.gamerent.model.dto.security.AuthenticationUserDto;

public interface AuthenticationService {
    AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto authenticationRequestDto);
}
