package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.entity.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameResponse gameToGameResponseDto(Game game);
}