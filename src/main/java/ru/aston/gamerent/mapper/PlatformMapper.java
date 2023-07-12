package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.PlatformResponseDto;
import ru.aston.gamerent.model.entity.Platform;

@Mapper(componentModel = "spring")
public interface PlatformMapper {

    PlatformResponseDto platformToPlatformResponseDto(Platform platform);
}
