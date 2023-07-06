package ru.aston.gamerent.model.dto.response;

import lombok.Data;
import ru.aston.gamerent.model.entity.Platform;
import java.time.LocalDateTime;

@Data
public class AccountResponseSimpleDto {
    private Long id;
    private LocalDateTime expirationTime;
    private Platform platform;
}