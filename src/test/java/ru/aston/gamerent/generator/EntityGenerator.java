package ru.aston.gamerent.generator;

import lombok.Getter;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.AccountsGames;
import ru.aston.gamerent.model.entity.ConfirmationToken;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.model.enumeration.CurrencyCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
            .value(BigDecimal.TEN)
            .build();

    Account account = Account.builder()
            .id(1L)
            .login("MiloLogin")
            .password("1q2w3e4r")
            .creationTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .expirationTime(LocalDateTime.now())
            .build();

    ConfirmationToken token = ConfirmationToken.builder()
            .id(1L)
            .user(user)
            .token(UUID.randomUUID())
            .creationTime(LocalDateTime.now())
            .build();

    Game game1 = Game.builder()
            .id(1L)
            .title("title1")
            .releaseDate(LocalDate.now())
            .description("desc1")
            .price(BigDecimal.ONE)
            .image("image1")
            .trailerUrl("trailer1")
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .accounts(Set.of(account))
            .build();

    List<Game> games = List.of(game1);

    Developer developer = Developer.builder()
            .id(1L)
            .title("CD Projekt")
            .description("description")
            .build();

    AccountsGames accountsGames1 = AccountsGames.builder()
            .id(1L)
            .assigningTime(LocalDateTime.now())
            .account(account)
            .game(game1)
            .build();

    {
        account.setAccountsGame(List.of(accountsGames1));
    }
}
