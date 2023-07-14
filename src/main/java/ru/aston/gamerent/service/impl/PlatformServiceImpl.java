package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.mapper.PlatformMapper;
import ru.aston.gamerent.model.dto.response.PlatformResponseDto;
import ru.aston.gamerent.repository.PlatformRepository;
import ru.aston.gamerent.service.PlatformService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;
    private final PlatformMapper platformMapper;

    @Override
    public List<PlatformResponseDto> findAllPlatforms() {
        return platformRepository.findAll().stream()
                .map(platformMapper::platformToPlatformResponseDto)
                .toList();
    }
}
