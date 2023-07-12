package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.mapper.DeveloperMapper;
import ru.aston.gamerent.model.dto.response.DeveloperResponseDto;
import ru.aston.gamerent.repository.DeveloperRepository;
import ru.aston.gamerent.service.DeveloperService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperMapper developerMapper;

    @Override
    public DeveloperResponseDto findDeveloperById(Long id) {
        return developerRepository.findById(id).map(developerMapper::developerToDeveloperResponse)
                .orElseThrow(() -> new NoEntityException("Game with this id doesn't exist"));
    }

    @Override
    public List<DeveloperResponseDto> findAllDevelopers() {
        return developerRepository.findAll().stream()
                .map(developerMapper::developerToDeveloperResponse)
                .toList();
    }
}