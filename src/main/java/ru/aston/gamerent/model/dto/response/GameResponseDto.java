package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.enumeration.GenreTitleEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record GameResponseDto(
         Long id,
         String title,
         DeveloperResponseSimpleDto developer,
         LocalDate releaseDate,
         String description,
         BigDecimal price,
         String image,
         String trailerUrl,
         List<String> screenshots,
         Set<GenreTitleEnum> genres
) {
}