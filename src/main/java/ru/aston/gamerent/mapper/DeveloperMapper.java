package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.response.DeveloperResponseDto;
import ru.aston.gamerent.model.dto.response.DeveloperResponseSimpleDto;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Game;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    DeveloperResponseSimpleDto developerToDeveloperSimpleDto(Developer developer);
    @Mapping(target = "games", source = "games")
    DeveloperResponseDto developerToDeveloperResponseDto(Developer developer);
    DeveloperResponseDto developerToDeveloperDto(Developer developer);
    default List<String> gamesToString (List <Game> games){
        return games.stream().map(Game::getTitle).toList();}
}
