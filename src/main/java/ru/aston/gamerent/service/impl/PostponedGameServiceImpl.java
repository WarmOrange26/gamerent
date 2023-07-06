package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.entity.PostponedGame;
import ru.aston.gamerent.repository.PostponedGameRepository;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.service.PostponedGameService;
import ru.aston.gamerent.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostponedGameServiceImpl implements PostponedGameService {
    private final PostponedGameRepository postponedGameRepository;
    private final UserService userService;
    private final GameService gameService;

    public List<PostponedGame> findByUserId(Long userId){
        return findByUserId(userId).stream().toList();
    }
    public Optional<PostponedGame> findByUserIdAndGameId(Long userId, Long gameId){
        return postponedGameRepository.findByUserIdAndGameId(userId, gameId);
    }
    public boolean postponeGame(Long userId, Long gameId) {
        Optional<PostponedGame> postponedGame =  postponedGameRepository.findByUserIdAndGameId(userId, gameId);
        if (postponedGame.isEmpty()) {
            postponedGameRepository.save(PostponedGame.builder()
                    .game(gameService.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found")))
                    .postponedTime(LocalDateTime.now())
                    .user(userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found")))
                    .build());
        }
        return true;
    }
}
