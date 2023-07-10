package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;

import static ru.aston.gamerent.model.dto.validation.ValidationConstants.EMAIL_PATTERN;
import static ru.aston.gamerent.model.dto.validation.ValidationConstants.PASSWORD_PATTERN;

@Builder
public record RegistrationUserRequestDto(

        String username,

        @NotBlank(message = "Email is mandatory!")
        @Pattern(regexp = EMAIL_PATTERN, message = "Invalid e-mail address")
        String email,

        String phone,

        @NotBlank(message = "Password is mandatory!")
        @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain only Latin letters and at least one digit, one upper case letter, one lower case letter and one special symbol (“@#$%”)")
        String password,

        String passwordConfirm,

        String firstName,

        String lastName,

        LocalDate birthDate) {
}
