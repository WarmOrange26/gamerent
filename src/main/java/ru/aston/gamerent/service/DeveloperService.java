package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.DeveloperResponseDto;

import java.util.List;

public interface DeveloperService {
    DeveloperResponseDto findDeveloperById(Long id);

    List<DeveloperResponseDto> findAllDevelopers();
}
