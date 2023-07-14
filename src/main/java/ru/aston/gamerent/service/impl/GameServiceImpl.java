package ru.aston.gamerent.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.mapper.GameMapper;
import ru.aston.gamerent.mapper.GenreMapper;
import ru.aston.gamerent.model.dto.request.GameRequest;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.dto.response.GenreResponseDto;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.Genre;
import ru.aston.gamerent.model.enumeration.GenreTitleEnum;
import ru.aston.gamerent.repository.DeveloperRepository;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.repository.GenreRepository;
import ru.aston.gamerent.service.GameService;

import java.util.HashSet;
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
    public List<GameResponseDto> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::gameToGameResponseDto)
                .toList();
    }

    @Override
    public GameResponseDto getGameById(Long id) {
        return gameRepository.findById(id)
                .map(gameMapper::gameToGameResponseDto)
                .orElseThrow(() -> new NoEntityException("Game with this id doesn't exists"));
    }

    @Override
    @Transactional
    public GameResponseDto saveGame(GameRequest gameRequest) {
        Developer developer = developerRepository.findByTitle(gameRequest.developer())
                .orElseThrow(() -> new NoEntityException("Developer no found title"));
        Game game = gameMapper.gameRequestToGame(gameRequest);
        game.setDeveloper(developer);
        List<Genre> genres = genreRepository.findAllByTitleIn(gameRequest.genres().stream()
                .map(genreResponse -> GenreTitleEnum.valueOf(genreResponse.title())).toList());
        game.setGenres(new HashSet<>(genres));
        Game saveGame = gameRepository.save(game);
        genreRepository.saveAll(game.getGenres());
        return gameMapper.gameToGameResponseDto(saveGame);
    }

    @Override
    public void updateGame(Long id, GameRequest gameRequest) {
        Developer developer = developerRepository.findByTitle(gameRequest.developer())
                .orElseThrow(() -> new NoEntityException("Developer no found title"));
        Game gameFromDB = gameRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Game with id " + id + " was not found"));

        Game updatedGame = gameMapper.gameRequestToGame(gameRequest, gameFromDB);
        updatedGame.setDeveloper(developer);

        List<Genre> genres = genreRepository.findAllByTitleIn(gameRequest.genres().stream()
                .map(genreResponse -> GenreTitleEnum.valueOf(genreResponse.title())).toList());
        updatedGame.setGenres(new HashSet<>(genres));

        gameRepository.saveAndFlush(updatedGame);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Set<GenreResponseDto> findAllGenres() {
        return genreRepository.findAll().stream().map(genreMapper::genreToGenreResponse).collect(Collectors.toSet());
    }
}