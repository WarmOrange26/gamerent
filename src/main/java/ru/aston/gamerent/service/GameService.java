package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<GameResponseDto> getAllGames();
    GameResponseDto getGameById(Long id);
    Optional<Game> findById(Long id);
}
