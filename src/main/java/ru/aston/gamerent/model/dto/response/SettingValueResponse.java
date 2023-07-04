package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.SettingValue}
 */
@Builder
public record SettingValueResponse(
        Long id,
        SettingResponse setting,
        Boolean value) {
}