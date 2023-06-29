package ru.aston.gamerent.model.dto.response;

import ru.aston.gamerent.model.entity.Role;
import ru.aston.gamerent.model.entity.SettingValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.User}
 */
public record UserResponse(
        Long id,
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        LocalDateTime registrationTime,
        LocalDateTime updateTime,
        String phone,
        LocalDate birthDate,
        Boolean isBlocked,
        List<SettingValue> settings,
        Set<Role> roles) {
}