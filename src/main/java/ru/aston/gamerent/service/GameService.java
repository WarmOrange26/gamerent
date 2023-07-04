package ru.aston.gamerent.service;

import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.response.GameResponse;

@Service
public interface GameService {
    GameResponse getGameById(Long id);
}
