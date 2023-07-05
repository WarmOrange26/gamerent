package ru.aston.gamerent.model.enumeration;

import lombok.Getter;

@Getter
public enum CurrencyCode {

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

    CurrencyCode(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }
}
