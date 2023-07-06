package ru.aston.gamerent.model.dto.request;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

import static ru.aston.gamerent.model.dto.validation.ValidationConstants.*;

public record RegistrationUserRequestDto(
        @NotBlank(message = "Username is mandatory!")
        @Size(max = 30, message = "Username length must be up to 30 characters")
        @Pattern(regexp = USERNAME_PATTERN, message = "Only Latin letters are allowed")
        String username,

        @NotBlank(message = "Email is mandatory!")
        @Pattern(regexp = EMAIL_PATTERN, message = "Invalid e-mail address")
        String email,

        @NotBlank(message = "Phone is mandatory!")
        @Pattern(regexp = PHONE_PATTERN, message = "Invalid phone")
        String phone,

        @NotBlank(message = "Password is mandatory!")
        @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain only Latin letters and at least one digit, one upper case letter, one lower case letter and one special symbol (“@#$%”)")
        String password,

        @Transient
        String passwordConfirm,

        @NotBlank(message = "First name is mandatory!")
        @Pattern(regexp = FIRST_NAME_PATTERN, message = "Invalid first name")
        String firstName,

        @NotBlank(message = "Last name is mandatory!")
        @Pattern(regexp = LAST_NAME_PATTERN, message = "Invalid last name")
        String lastName,

        @NotNull
        LocalDate birthDate) {
}
