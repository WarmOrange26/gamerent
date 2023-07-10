package ru.aston.gamerent.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.gamerent.exception.BadRequestException;
import ru.aston.gamerent.model.dto.security.AuthenticationRequestDto;
import ru.aston.gamerent.model.dto.security.AuthenticationUserDto;
import ru.aston.gamerent.model.enumeration.RoleNameEnum;
import ru.aston.gamerent.service.AuthenticationService;
import ru.aston.gamerent.web.security.jwt.JwtTokenProvider;

import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationApiController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationApiControllerTest {

    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

    public static final AuthenticationRequestDto LOGIN_DATA = AuthenticationRequestDto.builder()
            .email("jack.black@gmail.com")
            .password("gamerent")
            .build();

    public static final AuthenticationUserDto LOGGED_USER = AuthenticationUserDto.builder()
            .email("jack.black@gmail.com")
            .roleNames(Set.of(RoleNameEnum.ROLE_USER))
            .build();

    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNrLmJsYWNrQGdtYWlsLmNvbSIsInJvbGVzIjpbIl" +
            "JPTEVfVVNFUiJdLCJpYXQiOjE2ODg4MTQxODYsImV4cCI6MTY4ODgxNzc4Nn0.qxI6UM-OjXiGKOAe_JugARYAajFrXmNHO5qPt9aA468";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void shouldReturn200WhenLoginSuccessful() throws Exception {
        when(authenticationService.findByEmailAndPassword(LOGIN_DATA)).thenReturn(LOGGED_USER);
        when(jwtTokenProvider.createToken(LOGGED_USER)).thenReturn(TOKEN);
        mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(LOGIN_DATA)))
                .andExpect(status().isOk());
        verify(authenticationService, times(1)).findByEmailAndPassword(LOGIN_DATA);
        verify(jwtTokenProvider, times(1)).createToken(LOGGED_USER);
    }

    @Test
    void shouldReturn400WhenLoginNotSuccessful() throws Exception {
        when(authenticationService.findByEmailAndPassword(LOGIN_DATA))
                .thenThrow(new BadRequestException("Wrong email or password"));
        mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(LOGIN_DATA)))
                .andExpect(status().isBadRequest());
        verify(authenticationService, times(1)).findByEmailAndPassword(LOGIN_DATA);
    }
}