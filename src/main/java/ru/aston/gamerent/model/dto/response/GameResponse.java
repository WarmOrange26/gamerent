package ru.aston.gamerent.model.dto.response;

import lombok.Data;
import ru.aston.gamerent.model.entity.Developer;
import ru.aston.gamerent.model.entity.Genre;
import ru.aston.gamerent.model.entity.Screenshot;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class GameResponse {
    private Long id;
    private String title;
    private Developer developer;
    private LocalDate releaseDate;
    private String description;
    private BigDecimal price;
    private String image;
    private String trailerUrl;
    private List<Screenshot> screenshots;
    private Set<Genre> genres;
}