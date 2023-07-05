package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

@Builder
public record PlatformResponseDto(
        Long id,
        String name) {
}