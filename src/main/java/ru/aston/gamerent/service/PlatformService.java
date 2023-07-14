package ru.aston.gamerent.service;

import ru.aston.gamerent.model.dto.response.PlatformResponseDto;

import java.util.List;

public interface PlatformService {

    List<PlatformResponseDto> findAllPlatforms();
}
