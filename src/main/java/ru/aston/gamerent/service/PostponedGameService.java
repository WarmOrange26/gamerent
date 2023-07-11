package ru.aston.gamerent.service;

import ru.aston.gamerent.model.entity.PostponedGame;

import java.util.Optional;

public interface PostponedGameService {
    Optional<PostponedGame> findByUserIdAndGameId(Long userId, Long gameId);
    boolean postponeGame (Long userId, Long gameId);
}
