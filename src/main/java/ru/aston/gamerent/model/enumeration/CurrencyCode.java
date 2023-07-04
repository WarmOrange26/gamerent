package ru.aston.gamerent.model.enumeration;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    EUR("Евро"),
    RUB("Российский рубль"),
    USD("Доллар США");

    private final String nameCurrency;

    CurrencyCode(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }
}
