package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DeveloperResponseDto (
        String title,
        String description,
        List<String> games
) {

}