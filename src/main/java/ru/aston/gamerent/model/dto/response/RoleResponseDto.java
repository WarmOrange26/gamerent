package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;

@Builder
public record RoleResponseDto(
        Long id,
        RoleNameEnum name) {
}