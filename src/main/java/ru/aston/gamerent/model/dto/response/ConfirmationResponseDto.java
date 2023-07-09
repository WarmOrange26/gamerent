package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ConfirmationResponseDto(UUID token) {
}
