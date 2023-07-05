package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.mapper.GameMapper;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.service.GameService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

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
}