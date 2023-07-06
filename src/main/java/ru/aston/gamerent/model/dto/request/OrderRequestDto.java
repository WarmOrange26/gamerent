package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.util.List;

@Builder
public record OrderRequestDto(
        @NotNull(message = "Player id is mandatory!")
        @Positive(message = "Id value must be greater than zero!")
        Long userId,

        @NotNull(message = "Wallet id is mandatory!")
        @Positive(message = "Id value must be greater than zero!")
        Long walletId,

        @NotNull(message = "Games ids is mandatory!")
        List<Long> gameIds,

        @NotNull(message = "Number of periods is mandatory!")
        @Positive(message = "Id value must be greater than zero!")
        Integer periods) {
}