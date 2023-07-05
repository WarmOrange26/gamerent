package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.enumeration.SettingsNamesEnum;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Setting}
 */
@Builder
public record SettingResponse(
        Long id,
        SettingsNamesEnum settingName) {
}