package ru.aston.gamerent.service;

import ru.aston.gamerent.model.entity.PostponedGame;
import org.springframework.data.repository.query.Param;
import ru.aston.gamerent.model.entity.PostponedGame;
import java.util.List;
import java.util.Optional;

public interface PostponedGameService {
    Optional<PostponedGame> findByUserIdAndGameId(Long userId, Long gameId);
  
    boolean postponeGame (Long userId, Long gameId);

    List <PostponedGame> findByUserId(Long userId);
}
