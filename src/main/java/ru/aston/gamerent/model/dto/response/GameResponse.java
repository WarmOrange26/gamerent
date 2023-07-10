package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Screenshot;
import ru.aston.gamerent.model.enumeration.GenreTitleEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Game}
 */
@Builder
public record GameResponse(
        Long id,
        String title,
        Developer developer,
        LocalDate releaseDate,
        String description,
        BigDecimal price,
        String image,
        String trailerUrl,
        List<ScreenshotResponse> screenshots,
        Set<GenreResponse> genres
) {
}
