package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Account}
 */
@Builder
public record AccountResponse(
        Long id,
        String login,
        String password,
        LocalDateTime expirationTime,
        PlatformResponse platform) {
}