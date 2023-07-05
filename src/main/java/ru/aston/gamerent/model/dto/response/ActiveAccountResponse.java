package ru.aston.gamerent.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ActiveAccountResponse(
        String login,
        String password,
        LocalDateTime expirationTime,
        List<String> games) {
}