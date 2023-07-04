package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Platform}
 */
@Builder
public record PlatformResponse(
        Long id,
        String name) {
}