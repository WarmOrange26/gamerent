package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record AccountResponseDto(
        Long id,
        String login,
        String password,
        LocalDateTime expirationTime,
        PlatformResponseDto platform) {
}