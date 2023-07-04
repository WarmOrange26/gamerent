package ru.aston.gamerent.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.gamerent.model.dto.response.DeveloperResponse;
import ru.aston.gamerent.model.exception.NoEntityException;
import ru.aston.gamerent.repository.DeveloperRepository;
import ru.aston.gamerent.service.DeveloperService;
import ru.aston.gamerent.service.mapper.DeveloperMapper;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperMapper developerMapper;

    @Override
    @Transactional
    public DeveloperResponse findDeveloperById(Long id) {
        return developerRepository.findById(id).map(developerMapper::developerToDeveloperResponse)
                .orElseThrow(() -> new NoEntityException("Game with this id doesn't exist"));
    }
}
