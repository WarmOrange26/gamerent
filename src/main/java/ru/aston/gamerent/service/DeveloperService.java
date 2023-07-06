package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.DeveloperResponseDto;

public interface DeveloperService {
    DeveloperResponseDto findDeveloperById(Long id);
}
