package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.aston.gamerent.model.entity.SettingValue;

import java.time.LocalDate;
import java.util.List;

import static ru.aston.gamerent.model.dto.validation.ValidationConstants.EMAIL_PATTERN;
import static ru.aston.gamerent.model.dto.validation.ValidationConstants.FIRST_NAME_PATTERN;
import static ru.aston.gamerent.model.dto.validation.ValidationConstants.LAST_NAME_PATTERN;
import static ru.aston.gamerent.model.dto.validation.ValidationConstants.PASSWORD_PATTERN;
import static ru.aston.gamerent.model.dto.validation.ValidationConstants.PHONE_PATTERN;
import static ru.aston.gamerent.model.dto.validation.ValidationConstants.USERNAME_PATTERN;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.User}
 */
public record UserRequest(

        @NotBlank(message = "Username is mandatory!")
        @Size(max = 30, message = "Username length must be up to 30 characters")
        @Pattern(regexp = USERNAME_PATTERN, message = "Only Latin letters are allowed")
        String username,

        @NotBlank(message = "Email is mandatory!")
        @Pattern(regexp = EMAIL_PATTERN, message = "Invalid e-mail address")
        String email,

        @NotBlank(message = "Password is mandatory!")
        @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain only Latin letters and at least one digit, one upper case letter, one lower case letter and one special symbol (“@#$%”)")
        String password,

        @NotBlank(message = "First name is mandatory!")
        @Pattern(regexp = FIRST_NAME_PATTERN, message = "Invalid first name")
        String firstName,

        @NotBlank(message = "Last name is mandatory!")
        @Pattern(regexp = LAST_NAME_PATTERN, message = "Invalid last name")
        String lastName,

        @NotBlank(message = "Phone is mandatory!")
        @Pattern(regexp = PHONE_PATTERN, message = "Invalid phone")
        String phone,

        LocalDate birthDate,
        List<SettingValue> settings) {
}