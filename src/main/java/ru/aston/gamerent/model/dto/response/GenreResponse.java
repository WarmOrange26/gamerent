package ru.aston.gamerent.model.dto.response;


import lombok.Builder;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Setting}
 */

@Builder
public record GenreResponse(
        String title) {
}
