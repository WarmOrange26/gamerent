package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.enumeration.SettingsNamesEnum;

@Builder
public record SettingResponseDto(
        Long id,
        SettingsNamesEnum settingName) {
}