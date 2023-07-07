package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.entity.Game;

import java.util.List;

public interface GameService {

    GameResponse getGameById(Long id);

    List<Game> findAll();

    GameResponse saveGame(GameRequest gameRequest);

    void updateGame(Long id, GameRequest GameRequest);

    void deleteById(Long id);
}
