package ru.aston.gamerent.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.gamerent.model.dto.request.RegistrationUserRequestDto;
import ru.aston.gamerent.model.dto.response.ConfirmationResponseDto;
import ru.aston.gamerent.service.EmailService;
import ru.aston.gamerent.service.UserService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(RegistrationController.class)
@AutoConfigureMockMvc(addFilters = false)
class RegistrationControllerTest {

    private static final ConfirmationResponseDto TOKEN = new ConfirmationResponseDto(UUID.randomUUID());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private EmailService emailService;

    @Test
    void shouldShowRegistrationPageForNewUser() throws Exception {
        mockMvc
                .perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    void shouldReturn200WhenAddNewUser() throws Exception {
        when(userService.saveUser(isA(RegistrationUserRequestDto.class))).thenReturn(Optional.of(TOKEN));
        when(emailService.sendRegistrationMail("JD", "jd@example.com" , "Qwerty@123", TOKEN.token()))
                .thenReturn("email sent");
        mockMvc
                .perform(post("/registration")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "JD")
                        .param("email", "jd@example.com")
                        .param("phone", "375291112233")
                        .param("password", "Qwerty@123")
                        .param("passwordConfirm", "Qwerty@123")
                        .param("firstName", "Jake")
                        .param("lastName", "Dean")
                        .param("birtDate", String.valueOf(LocalDate.now())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("registrationMessage", "email sent"))
                .andExpect(view().name("index"));
    }

    @Test
    void shouldNotAddNewUserWithInvalidRequest() throws Exception {
        mockMvc
                .perform(post("/registration")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "JD")
                        .param("email", "jd@ex")
                        .param("phone", "375291112233")
                        .param("password", "Qwerty@123")
                        .param("passwordConfirm", "Qwerty@123")
                        .param("firstName", "Jake")
                        .param("lastName", "Dean")
                        .param("birtDate", String.valueOf(LocalDate.now())))
                .andExpect(model().attribute("registrationMessage", (String) null))
                .andExpect(view().name("registration"));
    }


    @Test
    void shouldReturn200WhenVerifyUserByToken() throws Exception {
        when(userService.confirmEmail(TOKEN.token())).thenReturn(true);
        mockMvc
                .perform(get("/registration/verify")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("token", TOKEN.token().toString()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("verifyMessage", "Email verified successfully!" +
                        " Now you can log in to the site."))
                .andExpect(view().name("index"));
    }

    @Test
    void shouldNotVerifyUserByInvalidToken() throws Exception {
        when(userService.confirmEmail(TOKEN.token())).thenReturn(false);
        mockMvc
                .perform(get("/registration/verify")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("token", TOKEN.token().toString()))
                .andExpect(model().attribute("verifyError", "Couldn't verify email!"))
                .andExpect(view().name("index"));
    }
}