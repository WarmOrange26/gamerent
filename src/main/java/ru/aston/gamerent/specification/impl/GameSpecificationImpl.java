package ru.aston.gamerent.specification.impl;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.aston.gamerent.model.dto.request.GameRequestDto;
import ru.aston.gamerent.model.entity.Platform;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.Game_;
import ru.aston.gamerent.model.entity.Developer_;
import ru.aston.gamerent.model.entity.Platform_;
import ru.aston.gamerent.specification.GameSpecification;

import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class GameSpecificationImpl implements GameSpecification {
    public static final String LIKE_PATTERN = "%%%s%%";

    @Override
    public Specification<Game> hasTitle(GameRequestDto gameRequestDto) {
        return isBlank(gameRequestDto.title()) ? null :
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get(Game_.title)),
                                String.format(LIKE_PATTERN,
                                        gameRequestDto.title().toLowerCase().trim()));
    }

    @Override
    public Specification<Game> hasDeveloper(GameRequestDto gameRequestDto) {
        return isBlank(gameRequestDto.developer()) ? null :
                (root, query, criteriaBuilder) -> {
                    Join<Game, Developer> developerJoin = root.join(Game_.developer);

                    return criteriaBuilder.equal(
                            criteriaBuilder.lower(developerJoin.get(Developer_.title)),
                            gameRequestDto.developer().toLowerCase());
                };
    }

    @Override
    public Specification<Game> hasMinPrice(GameRequestDto gameRequestDto) {
        return gameRequestDto.minPrice() == null ? null :
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(Game_.price),
                                gameRequestDto.minPrice());

    }

    @Override
    public Specification<Game> hasMaxPrice(GameRequestDto gameRequestDto) {
        return gameRequestDto.maxPrice() == null ? null :
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(Game_.price),
                                gameRequestDto.maxPrice());
    }

    @Override
    public Specification<Game> hasPlatform(GameRequestDto gameRequestDto) {
        return isBlank(gameRequestDto.platform())  ? null :
                (root, query, criteriaBuilder) -> {
                    Join<Game, Account> accountsGamesJoin = root.join("accounts");
                    Join<Account, Platform> platformJoin = accountsGamesJoin.join("platform");
                    return criteriaBuilder.like(
                            criteriaBuilder.lower(platformJoin.get(Platform_.name)),
                            String.format(LIKE_PATTERN,
                                    gameRequestDto.platform().toLowerCase().trim()));
                };

    }

    @Override
    public Specification<Game> hasYear(GameRequestDto gameRequestDto) {
        return gameRequestDto.releaseYear() == null ? null :
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.between(root.get(Game_.releaseDate),
                                LocalDate.of(gameRequestDto.releaseYear().getValue(), 1, 1),
                                LocalDate.of(gameRequestDto.releaseYear().getValue(), 12, 31));

    }


}
