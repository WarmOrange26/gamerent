package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.dto.response.GameResponse;
import ru.aston.gamerent.model.dto.response.GenreResponse;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.exception.NoEntityException;
import ru.aston.gamerent.repository.DeveloperRepository;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.repository.GenreRepository;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.service.mapper.GameMapper;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.gamerent.service.mapper.GenreMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final DeveloperRepository developerRepository;
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

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
    @Transactional
    public GameResponse saveGame(GameRequest gameRequest) {
        Developer developer = developerRepository.findByTitle(gameRequest.developer())
                .orElseThrow(() -> new NoEntityException("Developer no found title"));
        Game game = gameMapper.gameRequestToGame(gameRequest);
        game.setDeveloper(developer);
        Game saveGame = gameRepository.save(game);
        genreRepository.saveAll(game.getGenres());
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

    @Override
    public Set<GenreResponse> findAllGenres() {
        return genreRepository.findAll().stream().map(genreMapper::genreToGenreResponse).collect(Collectors.toSet());
    }
}