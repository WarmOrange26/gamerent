package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ActiveAccountResponseDto(
        String login,
        String password,
        LocalDateTime expirationTime,
        List<String> games) {
}