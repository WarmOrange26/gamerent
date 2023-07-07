package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.exception.NoEntityException;
import ru.aston.gamerent.repository.DeveloperRepository;
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
    private final DeveloperRepository developerRepository;

    @Override
    @Transactional
    public GameResponse getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Game with id " + id + " was not found"));
        return gameMapper.gameToGameResponseDto(game);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public GameResponse saveGame(GameRequest gameRequest) {
        Developer developer = developerRepository.findByTitle(gameRequest.developer())
                .orElseThrow(() -> new NoEntityException("Developer no found title"));
        Game game = gameMapper.gameRequestToGame(gameRequest);
        game.setDeveloper(developer);
        Game saveGame = gameRepository.saveAndFlush(game);
        return gameMapper.gameToGameResponseDto(saveGame);
    }

    @Override
    public void updateGame(Long id, GameRequest gameRequest) {
        Developer developer = developerRepository.findByTitle(gameRequest.developer())
                .orElseThrow(() -> new NoEntityException("Developer no found title"));
        Game gameFromDB = gameRepository.findById(id).orElseThrow(() -> new NoEntityException("Game with id " + id + " was not found"));
        Game game = gameMapper.gameRequestToGame(gameRequest, gameFromDB);
        game.setDeveloper(developer);
        gameRepository.saveAndFlush(game);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}