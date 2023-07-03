package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.User}
 */
@Builder
public record UserResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        LocalDateTime registrationTime,
        String phone,
        LocalDate birthDate,
        Boolean isBlocked,
        List<SettingValueResponse> settings,
        Set<RoleResponse> roles,
        List<WalletResponse> wallets,
        List<AccountResponse> accounts) {
}