package ru.aston.gamerent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.gamerent.model.dto.response.GameResponseDto;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.Genre;
import ru.aston.gamerent.model.entity.Screenshot;
import ru.aston.gamerent.model.enumeration.GenreTitleEnum;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GameMapper {
    //@Mapping(target = "developer", source = "developerResponseSimpleDto")
    @Mapping(target = "screenshots", source = "screenshots")
    @Mapping(target = "genres", source = "genres")
    GameResponseDto gameToGameResponseDto(Game game);

    default List<String> screenshotsUrlsToString(List<Screenshot> screenshots) {
        return screenshots.stream()
                .map(Screenshot::getUrl).toList();
    }

    default Set<GenreTitleEnum> genresToString(Set<Genre> genres) {
        return genres.stream()
                .map(Genre::getTitle).collect(Collectors.toSet());

    }
}