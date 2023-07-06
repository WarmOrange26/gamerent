package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Genre;
import ru.aston.gamerent.model.entity.Screenshot;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record GameResponseDto(
         Long id,
         String title,
         Developer developer,
         LocalDate releaseDate,
         String description,
         BigDecimal price,
         String image,
         String trailerUrl,
         List<Screenshot> screenshots,
         Set<Genre> genres
) {
}