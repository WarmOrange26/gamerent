package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
public record UserDto(
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
        List<SettingValueResponseDto> settings,
        Set<RoleResponseDto> roles) {
}