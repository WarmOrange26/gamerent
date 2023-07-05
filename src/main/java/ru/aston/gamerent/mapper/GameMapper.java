package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.entity.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameResponseDto gameToGameResponseDto(Game game);
}
