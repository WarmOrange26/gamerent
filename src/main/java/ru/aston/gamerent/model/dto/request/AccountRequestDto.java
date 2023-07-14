package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AccountRequestDto(
        @NotBlank
        String login,

        @NotBlank
        String password,

        @NotBlank
        String platformName) {
}
