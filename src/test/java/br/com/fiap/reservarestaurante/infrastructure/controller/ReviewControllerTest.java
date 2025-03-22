package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.ReviewDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateReviewUseCase;
import br.com.fiap.reservarestaurante.metadata.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @MockitoBean
    CreateReviewUseCase createReviewUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @IntegrationTest
    void shouldCreateReview() throws Exception {
        var resId = UUID.randomUUID().toString();
        var userId = UUID.randomUUID().toString();
        var rating = 3;
        var content = "Muito bom!";
        var reqDTO = new ReviewDTO(resId, userId, rating, content);
        var objMapper = new ObjectMapper();
        var request = objMapper.writeValueAsString(reqDTO);
        this.mockMvc
                .perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)).andDo(print())
                .andExpect(status().isCreated());

    }
}
