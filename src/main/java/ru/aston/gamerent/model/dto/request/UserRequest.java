package ru.aston.gamerent.model.dto.request;

import ru.aston.gamerent.model.entity.SettingValue;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.User}
 */
public record UserRequest(
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        String phone,
        LocalDate birthDate,
        List<SettingValue> settings) {
}