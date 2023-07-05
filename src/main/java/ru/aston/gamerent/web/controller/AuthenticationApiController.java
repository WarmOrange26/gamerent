package ru.aston.gamerent.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.gamerent.model.dto.security.AuthenticationRequestDto;
import ru.aston.gamerent.model.dto.security.AuthenticationUserDto;
import ru.aston.gamerent.service.AuthenticationService;
import ru.aston.gamerent.web.security.jwt.JwtTokenProvider;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthenticationApiController {
    public static final String EMAIL = "email";
    public static final String ACCESS_TOKEN = "access token";
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationUserDto user = authenticationService.findByEmailAndPassword(requestDto);

        Map<Object, Object> response = new HashMap<>();
        response.put(EMAIL, user.email());
        response.put(ACCESS_TOKEN, jwtTokenProvider.createToken(user));

        return ResponseEntity.ok(response);
    }
}
