package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import ru.aston.gamerent.model.dto.validation.EnumValidator;
import ru.aston.gamerent.model.enumeration.SettingsNamesEnum;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.SettingValue}
 */
@Builder
public record SettingValueRequest(
        @NotBlank(message = "Setting is mandatory!")
        @EnumValidator(
                enumClazz = SettingsNamesEnum.class,
                message = "Invalid setting name"
        )
        String settingName,

        @NotNull(message = "Value is mandatory!")
        Boolean value) {
}