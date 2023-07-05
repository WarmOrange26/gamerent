package ru.aston.gamerent.service;

import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.response.GameResponse;
import java.util.List;

@Service
public interface GameService {
    List<GameResponse> getAllGames();

    GameResponse getGameById(Long id);
}
