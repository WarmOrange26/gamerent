package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.mapper.GameMapper;
import ru.aston.gamerent.model.dto.request.GameRequestDto;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.service.GameService;
import ru.aston.gamerent.specification.GameSpecification;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final GameSpecification gameSpecification;

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
    public Page<GameResponseDto> findGamesByFilter(GameRequestDto gameRequestDto, Pageable pageable) {
        Specification<Game> specification = where(gameSpecification.hasTitle(gameRequestDto))
                .and(where(gameSpecification.hasMinPrice(gameRequestDto)))
                .and(where(gameSpecification.hasMaxPrice(gameRequestDto)))
                .and(where(gameSpecification.hasPlatform(gameRequestDto)))
                .and(where(gameSpecification.hasYear(gameRequestDto)))
                .and(where(gameSpecification.hasDeveloper(gameRequestDto)));
        return gameRepository.findAll(specification, pageable)
                .map(gameMapper::gameToGameResponseDto);

    }
}