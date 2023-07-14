package ru.aston.gamerent.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.mapper.GameMapper;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.PostponedGame;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.repository.CartRepository;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.service.CartService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Override
    public GameResponseDto addGameToCart(String email, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new IllegalArgumentException("Game not found"));
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        PostponedGame postponedGame = PostponedGame.builder()
                .game(game)
                .postponedTime(LocalDateTime.now())
                .user(user)
                .build();

        Game gameInCart = cartRepository.findByUserEmailAndGameId(email, gameId)
                .orElse(cartRepository.save(postponedGame))
                .getGame();

        return gameMapper.gameToGameResponseDto(gameInCart);
    }

    public List<GameResponseDto> findByUserEmail(String email) {
        return cartRepository.findByUserEmail(email).stream()
                .map(PostponedGame::getGame)
                .map(gameMapper::gameToGameResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteGameFromCart(String email, Long gameId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow();
        Game game = gameRepository.findById(gameId)
                .orElseThrow();

        cartRepository.deleteByUserAndGame(user, game);
    }
}