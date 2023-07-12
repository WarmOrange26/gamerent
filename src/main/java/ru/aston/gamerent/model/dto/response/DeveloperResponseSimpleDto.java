package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

@Builder
public record DeveloperResponseSimpleDto(
        Long id,
        String title
) {

}
