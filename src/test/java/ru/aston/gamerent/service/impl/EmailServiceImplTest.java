package ru.aston.gamerent.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    private static final String USER_INFO_SUCCESS = "A letter with registration data is sent to the e-mail" +
            " specified during registration. Please, verify you account before entering the site!";

    private static final String USER_INFO_ERROR = "Аn error occurred during registration email sending!";

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void sendRegistrationMail() {
        assertThat(emailService.sendRegistrationMail("", "", "", UUID.randomUUID()))
                .isEqualTo(USER_INFO_SUCCESS);
    }

    @Test
    void sendRegistrationMailThrowException() {
        doThrow(new RuntimeException()).when(javaMailSender).send(new SimpleMailMessage());

        assertThat(emailService.sendRegistrationMail("", "", "", UUID.randomUUID()))
                .isEqualTo(USER_INFO_ERROR);
    }
}