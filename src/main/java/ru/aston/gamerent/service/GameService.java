package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.entity.Game;

import java.util.List;

public interface GameService {

    GameResponse getGameById(Long id);

    List<Game> findAll();

    Game saveGame(Game game);

    void deleteById(Long id);
}
