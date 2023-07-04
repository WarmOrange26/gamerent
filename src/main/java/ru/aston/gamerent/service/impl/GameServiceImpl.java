package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.exception.NoEntityException;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.service.mapper.GameMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    @Override
    @Transactional
    public GameResponse getGameById(Long id) {
        return gameRepository.findById(id).map(gameMapper::gameToGameResponseDto)
                .orElseThrow(() -> new NoEntityException("Game with this id doesn't exists"));
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}