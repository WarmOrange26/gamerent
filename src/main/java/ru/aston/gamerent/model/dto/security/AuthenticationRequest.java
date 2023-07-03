package ru.aston.gamerent.model.dto.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static ru.aston.gamerent.model.dto.validation.ValidationConstants.EMAIL_PATTERN;

public record AuthenticationRequest(
        @NotBlank(message = "Email is mandatory!")
        @Pattern(regexp = EMAIL_PATTERN, message = "Invalid e-mail address")
        String email,

        @NotBlank(message = "Password is mandatory!")
        String password) {
}
