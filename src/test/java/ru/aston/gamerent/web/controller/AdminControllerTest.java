package ru.aston.gamerent.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.gamerent.model.dto.request.AccountRequestDto;
import ru.aston.gamerent.model.dto.response.AccountResponseInfoDto;
import ru.aston.gamerent.service.AccountService;

import java.time.LocalDateTime;
import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private static final AccountRequestDto ACCOUNT_REQUEST_DTO = AccountRequestDto.builder()
            .login("example")
            .password("example1@")
            .platformName("PC")
            .build();

    private static final AccountResponseInfoDto ACCOUNT_RESPONSE_INFO_DTO = AccountResponseInfoDto.builder()
            .id(new Random().nextLong())
            .login("example")
            .password("example1@")
            .creationTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .platformName("PC")
            .build();

    @Test
    void shouldShowCreationPageForNewAccount() throws Exception {
        mockMvc
                .perform(get("/admin/accounts"))
                .andExpect(status().isOk())
                .andExpect(view().name("accounts"));
    }

    @Test
    void shouldSaveAccountWithCorrectRequest() throws Exception {
        when(accountService.saveAccount(ACCOUNT_REQUEST_DTO))
                .thenReturn(ACCOUNT_RESPONSE_INFO_DTO);
        mockMvc
                .perform(post("/admin/accounts")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "example")
                        .param("password", "example1@")
                        .param("platformName", "PC"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("success", ACCOUNT_RESPONSE_INFO_DTO.toString()))
                .andExpect(view().name("accounts"));
    }

    @Test
    void shouldNotSaveAccountWithInvalidRequest() throws Exception {
        mockMvc
                .perform(post("/admin/accounts")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "example")
                        .param("password", "example1@"))
                .andExpect(view().name("accounts"));
    }
}