package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.security.AuthenticationRequest;
import ru.aston.gamerent.model.dto.security.AuthenticationUser;
import ru.aston.gamerent.model.exception.BadRequestException;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.service.AuthenticationService;
import ru.aston.gamerent.service.mapper.AuthenticationUserMapper;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationUserMapper authenticationUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationUser findByEmailAndPassword(AuthenticationRequest authenticationRequest) {
        return userRepository.findByEmail(authenticationRequest.email())
                .filter(user -> passwordEncoder.matches(authenticationRequest.password(), user.getPassword()))
                .map(authenticationUserMapper::userToAuthenticationUserDto)
                .orElseThrow(() -> new BadRequestException("Wrong email or password"));
    }
}