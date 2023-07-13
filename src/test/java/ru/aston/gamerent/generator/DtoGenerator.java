package ru.aston.gamerent.generator;

import lombok.Getter;
import ru.aston.gamerent.model.dto.request.OrderRequestDto;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.request.UserRequestDto;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponseDto;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.model.dto.response.DeveloperResponseDto;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.dto.response.UserDto;
import ru.aston.gamerent.model.dto.response.UserResponseDto;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.ConfirmationToken;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DtoGenerator {

    private static final EntityGenerator entityGeneratorTest = new EntityGenerator();

    private final User user = entityGeneratorTest.getUser();
    private final ConfirmationToken token = entityGeneratorTest.getToken();
    private final Account account = entityGeneratorTest.getAccount();
    private final Wallet wallet = entityGeneratorTest.getWallet();
    private final List<Game> games = entityGeneratorTest.getGames();
    private final Developer developer = entityGeneratorTest.getDeveloper();
    private final Game game = entityGeneratorTest.getGame1();

    UserResponseDto userResponseDto = UserResponseDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .registrationTime(user.getRegistrationTime())
            .phone(user.getPhone())
            .birthDate(user.getBirthDate())
            .isBlocked(user.getIsBlocked())
            .build();

    UserDto userDto = UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .registrationTime(user.getRegistrationTime())
            .phone(user.getPhone())
            .birthDate(user.getBirthDate())
            .isBlocked(user.getIsBlocked())
            .build();

    UserRequestDto userRequestDto = UserRequestDto.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .phone(user.getPhone())
            .birthDate(user.getBirthDate())
            .build();

    RegistrationUserRequestDto registrationUserRequestDto = RegistrationUserRequestDto.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .phone(user.getPhone())
            .password(user.getPassword())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .birthDate(user.getBirthDate())
            .build();

    ConfirmationResponseDto confirmationResponseDto = ConfirmationResponseDto.builder()
            .token(token.getToken())
            .build();

    List<Long> gameIds = games.stream().mapToLong(Game::getId).boxed().toList();

    OrderRequestDto orderRequestDto = OrderRequestDto.builder()
            .userId(user.getId())
            .walletId(wallet.getId())
            .gameIds(gameIds)
            .periods(7)
            .build();

    ActiveAccountResponseDto accountResponseDto = ActiveAccountResponseDto.builder()
            .login(account.getLogin())
            .password(account.getPassword())
            .expirationTime(account.getExpirationTime())
            .games(games.stream().map(Game::getTitle).collect(Collectors.toList()))
            .build();

    DeveloperResponseDto developerResponseDto = DeveloperResponseDto.builder()
            .id(developer.getId())
            .title(developer.getTitle())
            .description(developer.getDescription())
            .build();

    GameResponseDto gameResponseDto  = GameResponseDto.builder()
            .id(game.getId())
            .title(game.getTitle())
            .releaseDate(game.getReleaseDate())
            .description(game.getDescription())
            .price(game.getPrice())
            .image(game.getImage())
            .trailerUrl(game.getTrailerUrl())
            .build();
}
