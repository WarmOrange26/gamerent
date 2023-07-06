package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.GameResponseDto;
import java.util.List;

public interface GameService {
    List<GameResponseDto> getAllGames();

    GameResponseDto getGameById(Long id);
}
