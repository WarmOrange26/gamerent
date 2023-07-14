package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.GameResponseDto;

import java.util.List;

public interface CartService {

    GameResponseDto addGameToCart(String email, Long gameId);

    List<GameResponseDto> findByUserEmail(String email);

    void deleteGameFromCart(String username, Long gameId);
}