package ru.aston.gamerent.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.model.entity.ConfirmationToken;
import ru.aston.gamerent.model.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ConfirmationTokenMapperImpl.class})
class ConfirmationTokenMapperTest {

    @Autowired
    private ConfirmationTokenMapper confirmationTokenMapper;

    private static final ConfirmationToken CONFIRMATION_TOKEN = ConfirmationToken.builder()
            .id(1L)
            .token(UUID.randomUUID())
            .creationTime(LocalDateTime.now())
            .user(new User())
            .build();


    @Test
    void shouldMapConfirmationTokenToDto() {
        ConfirmationResponseDto confirmationDto = confirmationTokenMapper.confirmationTokenToDto(CONFIRMATION_TOKEN);
        assertNotNull(confirmationDto);
        assertEquals(CONFIRMATION_TOKEN.getToken(), confirmationDto.token());
    }
}