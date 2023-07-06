package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.security.AuthenticationRequest;
import ru.aston.gamerent.model.dto.security.AuthenticationUser;

public interface AuthenticationService {
    AuthenticationUser findByEmailAndPassword(AuthenticationRequest authenticationRequest);
}
