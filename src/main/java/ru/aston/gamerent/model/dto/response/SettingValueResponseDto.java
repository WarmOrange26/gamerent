package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

@Builder
public record SettingValueResponseDto(
        Long id,
        SettingResponseDto setting,
        Boolean value) {
}