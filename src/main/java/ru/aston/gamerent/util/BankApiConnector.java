package ru.aston.gamerent.service.util;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.aston.gamerent.model.enumeration.CurrencyCode;
import ru.aston.gamerent.model.exception.CurrencyConvertingException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;

@Component
public class BankApiConnector {

    @Value("${game-rent.currency.data-path}")
    private String path;

    public BigDecimal getCurrencyValue(CurrencyCode currencyCode) {
        if (currencyCode.equals(CurrencyCode.RUB)) {
            return BigDecimal.ONE;
        }
        else {
            return getTodayValue(currencyCode);
        }
    }

    private BigDecimal getTodayValue(CurrencyCode currencyCode) {
        try (InputStream stream = new URL(path).openStream()) {
            String jsonData = new String(stream.readAllBytes());
            JsonReader reader = Json.createReader(new StringReader(jsonData));
            JsonObject jsonObject = reader.readObject().getJsonObject("Valute");
            JsonObject currentCurrency = jsonObject.getJsonObject(String.valueOf(currencyCode));
            return BigDecimal.valueOf(Double.parseDouble(String.valueOf(currentCurrency.get("Value"))));
        }
        catch (IOException e) {
            throw new CurrencyConvertingException("Unable to convert currency. Try again later :)");
        }
    }
}
