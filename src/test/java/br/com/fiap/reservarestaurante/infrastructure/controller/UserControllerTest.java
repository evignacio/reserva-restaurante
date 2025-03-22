package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.core.dto.CreateUserDTO;
import br.com.fiap.reservarestaurante.core.dto.UserDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateUserUseCase;
import br.com.fiap.reservarestaurante.core.usecase.DeleteUserUseCase;
import br.com.fiap.reservarestaurante.core.usecase.FindUserUseCase;
import br.com.fiap.reservarestaurante.metadata.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockitoBean
    CreateUserUseCase createUserUseCase;

    @MockitoBean
    DeleteUserUseCase deleteUserUseCase;

    @MockitoBean
    FindUserUseCase findUserUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @IntegrationTest
    void shouldFindUser() throws Exception {
        var id = UUID.randomUUID().toString();
        var user = new UserDTO(id, "a", "a@fiap.com.br");
        var objMapper = new ObjectMapper();
        var response = objMapper.writeValueAsString(user);
        when(findUserUseCase.execute("a@fiap.com.br")).thenReturn(user);
        this.mockMvc.perform(get("/users/a@fiap.com.br")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(response)));
    }

    @Test
    @IntegrationTest
    void shouldDeleteUser() throws Exception {
        var id = UUID.randomUUID();
        this.mockMvc.perform(delete("/users/" + id)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @IntegrationTest
    void shouldCreateUser() throws Exception {
        var req = new CreateUserDTO("abc", "bcd", 34, "a@fiap.com.br");
        var user = new User("abc", "bcd", 34, "a@fiap.com.br");
        var objMapper = new ObjectMapper();
        var reqString = objMapper.writeValueAsString(req);
        when(createUserUseCase.execute(req)).thenReturn(user);
        this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(reqString)).andDo(print()).andExpect(status().isCreated());
    }
}
