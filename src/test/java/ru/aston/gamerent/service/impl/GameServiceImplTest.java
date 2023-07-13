package ru.aston.gamerent.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.generator.DtoGenerator;
import ru.aston.gamerent.generator.EntityGenerator;
import ru.aston.gamerent.mapper.GameMapper;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.repository.GameRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameMapper gameMapper;

    @InjectMocks
    private GameServiceImpl gameService;

    private Game game;
    private GameResponseDto gameResponseDto;

    @BeforeEach
    void setup() {
        EntityGenerator entityGenerator = new EntityGenerator();
        DtoGenerator dtoGenerator = new DtoGenerator();
        game = entityGenerator.getGame1();
        gameResponseDto = dtoGenerator.getGameResponseDto();
    }

    @Test
    void getAllGames() {
        when(gameRepository.findAll()).thenReturn(List.of(game));
        when(gameMapper.gameToGameResponseDto(game)).thenReturn(gameResponseDto);

        assertThat(gameService.getAllGames()).isEqualTo(List.of(gameResponseDto));
    }

    @Test
    void getAllGamesEmptyList() {
        when(gameRepository.findAll()).thenReturn(Collections.emptyList());

        assertThat(gameService.getAllGames()).isEqualTo(Collections.emptyList());
    }

    @Test
    void getGameById() {
        when(gameRepository.findById(game.getId())).thenReturn(Optional.ofNullable(game));
        when(gameMapper.gameToGameResponseDto(game)).thenReturn(gameResponseDto);

        assertThat(gameService.getGameById(game.getId())).isEqualTo(gameResponseDto);
    }

    @Test
    void getGameByIdThrowException() {
        when(gameRepository.findById(game.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gameService.getGameById(game.getId()))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("Game with id " + game.getId() + " was not found");
    }
}