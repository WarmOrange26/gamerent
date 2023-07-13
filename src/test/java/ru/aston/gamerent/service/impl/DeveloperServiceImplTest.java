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
import ru.aston.gamerent.mapper.DeveloperMapper;
import ru.aston.gamerent.model.dto.response.DeveloperResponseDto;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.repository.DeveloperRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceImplTest {

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private DeveloperMapper developerMapper;

    @InjectMocks
    private DeveloperServiceImpl developerService;

    private Developer developer;
    private DeveloperResponseDto developerResponseDto;

    @BeforeEach
    void setup() {
        EntityGenerator entityGenerator = new EntityGenerator();
        DtoGenerator dtoGenerator = new DtoGenerator();
        developer = entityGenerator.getDeveloper();
        developerResponseDto = dtoGenerator.getDeveloperResponseDto();
    }

    @Test
    void findDeveloperById() {
        when(developerRepository.findById(developer.getId())).thenReturn(Optional.ofNullable(developer));
        when(developerMapper.developerToDeveloperResponse(developer)).thenReturn(developerResponseDto);

        assertThat(developerService.findDeveloperById(developer.getId())).isEqualTo(developerResponseDto);
    }

    @Test
    void findDeveloperByIdThrowException() {
        when(developerRepository.findById(developer.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> developerService.findDeveloperById(developer.getId()))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("Developer with id " + developer.getId() + " was not found");
    }
}