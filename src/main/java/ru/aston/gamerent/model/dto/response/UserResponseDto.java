package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
public record UserResponseDto(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        LocalDateTime registrationTime,
        String phone,
        LocalDate birthDate,
        Boolean isBlocked,
        List<SettingValueResponseDto> settings,
        Set<RoleResponseDto> roles,
        List<WalletResponseDto> wallets,
        List<AccountResponseDto> accounts) {
}