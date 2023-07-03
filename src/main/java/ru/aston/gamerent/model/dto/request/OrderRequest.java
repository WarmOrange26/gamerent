package ru.aston.gamerent.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderRequest(
        @NotNull(message = "Player id is mandatory!")
        @Positive
        Long playerId,
        @NotNull(message = "Wallet id is mandatory!")
        @Positive
        Long walletId,
        @NotNull(message = "Games ids is mandatory!")
        List<Long> gameIds,
        @NotNull(message = "Number of periods is mandatory!")
        @Positive
        Integer periods) {
}
