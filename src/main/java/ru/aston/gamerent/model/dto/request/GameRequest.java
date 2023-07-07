package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Game}
 */
@Builder
public record GameRequest(
        Long id,
        @NotNull(message = "The title of the game is mandatory!")
        String title,
        @NotNull(message = "The title of the developer is mandatory!")
        String developer,
        @NotNull(message = "The releaseDate is mandatory!")
        LocalDate releaseDate,
        @NotNull(message = "The description is mandatory!")
        String description,
        @NotNull(message = "The price is mandatory!")
        BigDecimal price,
        @URL
        @NotNull(message = "The image is mandatory!")
        String image,
        @URL
        @NotNull(message = "The trailer is mandatory!")
        String trailerUrl
) {
}
