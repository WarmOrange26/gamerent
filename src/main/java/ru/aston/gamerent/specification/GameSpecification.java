package ru.aston.gamerent.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.aston.gamerent.model.dto.request.GameRequestDto;
import ru.aston.gamerent.model.entity.Game;

public interface GameSpecification {

    Specification<Game> hasTitle(GameRequestDto gameRequestDto);

    Specification<Game> hasDeveloper(GameRequestDto gameRequestDto);

    Specification<Game> hasPlatform(GameRequestDto gameRequestDto);

    Specification<Game> hasYear(GameRequestDto gameRequestDto);

    Specification<Game> hasMinPrice(GameRequestDto gameRequestDto);

    Specification<Game> hasMaxPrice(GameRequestDto gameRequestDto);

}
