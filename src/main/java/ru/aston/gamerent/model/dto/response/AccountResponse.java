package ru.aston.gamerent.model.dto.response;

import java.time.LocalDateTime;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Account}
 */
public record AccountResponse(
        Long id,
        String login,
        String password,
        LocalDateTime creationTime,
        LocalDateTime updateTime,
        LocalDateTime expirationTime,
        PlatformResponse platform) {
}