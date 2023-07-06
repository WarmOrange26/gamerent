package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import ru.aston.gamerent.model.enumeration.CurrencyCodeEnum;
import java.math.BigDecimal;

@Builder
public record WalletResponseDto(
        Long id,
        CurrencyCodeEnum currency,
        BigDecimal value) {
}