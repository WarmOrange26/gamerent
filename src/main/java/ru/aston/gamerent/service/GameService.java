package ru.aston.gamerent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.aston.gamerent.model.dto.request.GameRequestDto;
import ru.aston.gamerent.model.dto.response.GameResponseDto;

import java.util.List;

public interface GameService {
    List<GameResponseDto> getAllGames();

    GameResponseDto getGameById(Long id);

    Page<GameResponseDto> findGamesByFilter(GameRequestDto gameRequestDto, Pageable pageable);
}
