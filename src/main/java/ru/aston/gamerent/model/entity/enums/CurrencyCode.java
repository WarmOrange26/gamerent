package ru.aston.gamerent.model.entity.enums;

public enum CurrencyCode {
    EUR("Евро"),
    RUB("Российский рубль"),
    USD("Доллар США");

    public final String nameCurrency;

    CurrencyCode(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }
}
