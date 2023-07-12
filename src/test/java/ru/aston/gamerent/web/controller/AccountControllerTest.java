package ru.aston.gamerent.web.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.aston.gamerent.model.dto.request.OrderRequestDto;

import java.util.List;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase(refresh = BEFORE_EACH_TEST_METHOD)
@Sql("/test-data.sql")
@AutoConfigureMockMvc
@WithUserDetails("netus.et@aol.com")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void buyAccounts() throws Exception {
        OrderRequestDto request = OrderRequestDto.builder()
                .userId(1L)
                .walletId(1L)
                .gameIds(List.of(1L, 2L, 3L))
                .periods(1)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonContent = mapper.writeValueAsString(request);

        mockMvc.perform(post("/api/vi/account/buying").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].login").value("Qtz22kmf2QK"))
                .andExpect(jsonPath("$[1].password").value("YsU2f5U8q7yF"))
                .andExpect(jsonPath("$[2].games").isArray())
                .andExpect(jsonPath("$[2].games[1]").value("semper auctor. Mauris vel"));
    }
}