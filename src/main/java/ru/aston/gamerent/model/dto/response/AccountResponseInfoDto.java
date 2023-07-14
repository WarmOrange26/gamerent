package ru.aston.gamerent.model.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AccountResponseInfoDto(Long id,
                                     String login,
                                     String password,
                                     String platformName,
                                     LocalDateTime creationTime,
                                     LocalDateTime updateTime) {
}
