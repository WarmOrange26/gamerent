package ru.aston.gamerent.model.dto.response;

import ru.aston.gamerent.model.enumeration.CurrencyCode;

import java.math.BigDecimal;

/**
 * DTO for {@link ru.aston.gamerent.model.entity.Wallet}
 */
public record WalletResponse(
        Long id,
        CurrencyCode currency,
        BigDecimal value) {
}