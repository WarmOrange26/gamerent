package ru.aston.gamerent.service;


import ru.aston.gamerent.dto.AuthenticationRequestDto;
import ru.aston.gamerent.dto.AuthenticationUserDto;

public interface AuthenticationService {

    AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto);
}
