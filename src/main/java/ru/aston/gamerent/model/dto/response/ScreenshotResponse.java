package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Screenshot}
 */

@Builder
public record ScreenshotResponse(
        String url) {
}
