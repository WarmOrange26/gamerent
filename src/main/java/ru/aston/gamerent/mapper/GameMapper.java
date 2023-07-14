package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.dto.response.GenreResponseDto;
import ru.aston.gamerent.model.dto.response.ScreenshotResponseDto;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.Genre;
import ru.aston.gamerent.model.entity.Screenshot;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameResponseDto gameToGameResponseDto(Game game);

    List<ScreenshotResponseDto> screenshotsToScreenshotsResponse(List<Screenshot> screenshots);

    Set<GenreResponseDto> genresToGenresResponse(Set<Genre> genres);

    @Mapping(target = "developer", ignore = true)
    Game gameRequestToGame(GameRequest gameRequest);

    @Mapping(target = "developer", ignore = true)
    default Game gameRequestToGame(GameRequest gameRequest, Game gameFromDB) {
        gameFromDB.setTitle(gameRequest.title());
        gameFromDB.setReleaseDate(gameRequest.releaseDate());
        gameFromDB.setPrice(gameRequest.price());
        gameFromDB.setDescription(gameRequest.description());
        gameFromDB.setImage(gameRequest.image());
        gameFromDB.setTrailerUrl(gameRequest.trailerUrl());
        return gameFromDB;
    }
}
