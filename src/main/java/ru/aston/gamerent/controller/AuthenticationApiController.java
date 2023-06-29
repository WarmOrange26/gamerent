package ru.aston.gamerent.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.gamerent.dto.AuthenticationRequestDto;
import ru.aston.gamerent.dto.AuthenticationUserDto;
import ru.aston.gamerent.exception.BadRequestException;
import ru.aston.gamerent.security.jwt.JwtTokenProvider;
import ru.aston.gamerent.service.AuthenticationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthenticationApiController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationUserDto user = authenticationService.findByEmailAndPassword(requestDto);
        if (user == null) {
            throw new BadRequestException("Wrong email or password");
        }
        Map<Object, Object> response = new HashMap<>();
        response.put("email", user.email());
        response.put("access token", jwtTokenProvider.createToken(user));
        return ResponseEntity.ok(response);
    }
}
