package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Year;

@Builder
public record GameRequestDto(
        @Pattern(regexp = "[\\sa-zA-Z0-9]*", message = "Title should consists of latin letters and numbers")
        String title,
        @Pattern(regexp = "[\\sa-zA-Z0-9]*", message = "Platform should consists latin letters and numbers")
        String platform,
        String developer,
        @PastOrPresent(message = "Year must be in the past")
        Year releaseYear,
        @Positive(message = "Min price should be positive")
        BigDecimal minPrice,
        @Positive(message = "Max price should be positive")
        BigDecimal maxPrice) {
}

