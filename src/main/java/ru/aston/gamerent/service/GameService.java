package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.dto.response.GenreResponseDto;

import java.util.List;
import java.util.Set;

public interface GameService {
    List<GameResponseDto> getAllGames();

    GameResponseDto getGameById(Long id);

    GameResponseDto saveGame(GameRequest gameRequest);

    void updateGame(Long id, GameRequest gameRequest);

    void deleteById(Long id);

    Set<GenreResponseDto> findAllGenres();
}
