package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.DeveloperResponseDto;
import ru.aston.gamerent.model.entity.Developer;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    DeveloperResponseDto developerToDeveloperResponse(Developer developer);
}
