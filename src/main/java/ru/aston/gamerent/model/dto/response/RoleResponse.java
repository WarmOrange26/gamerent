package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Role}
 */
@Builder
public record RoleResponse(
        Long id,
        RoleNameEnum name) {
}