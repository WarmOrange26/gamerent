package ru.aston.gamerent.model.dto.response;

import ru.aston.gamerent.model.entity.Setting;

import java.io.Serializable;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.SettingValue}
 */
public record SettingValueResponse(
        Long id,
        Setting setting,
        Boolean value) {
}