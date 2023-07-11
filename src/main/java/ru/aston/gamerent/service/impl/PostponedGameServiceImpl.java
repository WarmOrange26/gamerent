package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.entity.PostponedGame;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.repository.PostponedGameRepository;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.service.PostponedGameService;
import ru.aston.gamerent.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostponedGameServiceImpl implements PostponedGameService {
    private final PostponedGameRepository postponedGameRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    @Override
    public Optional<PostponedGame> findByUserIdAndGameId(Long userId, Long gameId){
        return postponedGameRepository.findByUserIdAndGameId(userId, gameId);
    }
    @Override
    public boolean postponeGame(Long userId, Long gameId) {
        if (postponedGameRepository.findByUserIdAndGameId(userId, gameId).isEmpty()) {
            postponedGameRepository.save(PostponedGame.builder()
                    .game(gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found")))
                    .postponedTime(LocalDateTime.now())
                    .user(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found")))
                    .build());
        }
        return true;
    }
}
