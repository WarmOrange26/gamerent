package ru.aston.gamerent.service.mapper;

import org.mapstruct.Mapper;
import ru.aston.gamerent.model.dto.response.GenreResponse;
import ru.aston.gamerent.model.entity.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponse genreToGenreResponse (Genre genre);

}
