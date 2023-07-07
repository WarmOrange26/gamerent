package ru.aston.gamerent.model.enumeration;

import lombok.Getter;

@Getter
public enum CurrencyCodeEnum {
    RUB("Российский рубль"),
    BYN("Белорусский рубль"),
    EUR("Евро"),
    USD("Доллар США"),
    GEL("Грузинский лари"),
    KZT("Казахстанских тенге"),
    KGS("Киргизских сомов"),
    TJS("Таджикских сомони"),
    UZS("Узбекских сумов");

    private final String nameCurrency;

    CurrencyCodeEnum(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }
}
