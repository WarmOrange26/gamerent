package ru.aston.gamerent.generator;

import lombok.Getter;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.model.enumeration.CurrencyCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EntityGenerator {

    User user = User.builder()
            .id(1L)
            .username("milo")
            .email("milo666@gmail.com")
            .password("Qwerty12#")
            .firstName("Milo")
            .lastName("Lil")
            .registrationTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .phone("12345678912")
            .birthDate(LocalDate.now())
            .isBlocked(false)
            .build();

    Wallet wallet = Wallet.builder()
            .id(1L)
            .currency(CurrencyCodeEnum.RUB)
            .value(BigDecimal.ONE)
            .build();

    Account account = Account.builder()
            .id(1L)
            .login("MiloLogin")
            .password("1q2w3e4r")
            .creationTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .expirationTime(LocalDateTime.now())
            .build();
}
