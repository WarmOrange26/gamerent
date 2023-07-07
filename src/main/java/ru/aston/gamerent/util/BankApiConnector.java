package ru.aston.gamerent.util;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.aston.gamerent.exception.CurrencyConvertingException;
import ru.aston.gamerent.model.enumeration.CurrencyCodeEnum;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;

@Component
public class BankApiConnector {
    public static final String VALUTE = "Valute";
    public static final String VALUE = "Value";

    @Value("${game-rent.currency.data-path}")
    private String path;

    public BigDecimal getCurrencyValue(CurrencyCodeEnum currencyCodeEnum) {
        return (currencyCodeEnum.equals(CurrencyCodeEnum.RUB))
                ? BigDecimal.ONE
                : getTodayValue(currencyCodeEnum);
    }

    private BigDecimal getTodayValue(CurrencyCodeEnum currencyCodeEnum) {
        try (InputStream stream = new URL(path).openStream()) {
            String jsonData = new String(stream.readAllBytes());

            JsonObject currentCurrency = Json.createReader(new StringReader(jsonData))
                    .readObject()
                    .getJsonObject(VALUTE)
                    .getJsonObject(String.valueOf(currencyCodeEnum));

            return BigDecimal.valueOf(Double.parseDouble(String.valueOf(currentCurrency.get(VALUE))));
        } catch (IOException e) {
            throw new CurrencyConvertingException("Unable to convert currency. Try again later :)");
        }
    }
}
