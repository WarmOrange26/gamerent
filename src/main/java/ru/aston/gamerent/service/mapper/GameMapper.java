package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.dto.response.GenreResponse;
import ru.aston.gamerent.model.dto.response.ScreenshotResponse;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.Genre;
import ru.aston.gamerent.model.entity.Screenshot;
import ru.aston.gamerent.model.enumeration.GenreTitleEnum;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface GameMapper {

    GameResponse gameToGameResponseDto(Game game);

    List<ScreenshotResponse> screenshotsToScreenshotsResponse(List<Screenshot> screenshots);

    Set<GenreResponse> genresToGenresResponse(Set<Genre> genres);

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