package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.DeveloperResponse;

public interface DeveloperService {
    DeveloperResponse findDeveloperById(Long id);
}
