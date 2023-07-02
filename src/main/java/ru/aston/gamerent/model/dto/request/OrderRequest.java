package ru.aston.gamerent.model.dto.request;

import java.util.List;

public record OrderRequest(
        Long playerId,
        Long walletId,
        List<Long> gameIds,
        Integer periods) {
}
