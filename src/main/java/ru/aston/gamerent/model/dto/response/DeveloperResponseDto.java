package ru.aston.gamerent.model.dto.response;

import lombok.Builder;
import lombok.Data;
import ru.aston.gamerent.model.entity.Game;
import java.util.List;

@Data
@Builder
public class DeveloperResponseDto {
    private Long id;
    private String title;
    private String description;
    private List<Game> games;
}