package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.GenreResponseDto;
import ru.aston.gamerent.model.entity.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponseDto genreToGenreResponse (Genre genre);

}