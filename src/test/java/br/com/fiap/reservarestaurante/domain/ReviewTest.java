package br.com.fiap.reservarestaurante.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReviewTest {

    @Test
    void shouldCreateReview() {
        assertDoesNotThrow(() -> new Review("xpto", "xpto2", 5, "Test review"));
    }

    @Test
    void shouldReturnExceptionIdRestaurantNull() {
        var exception = catchException(() -> new Review(null, "xpto2", 5, "Test review"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("IdRestaurant cannot be null");
    }

    @Test
    void shouldReturnExceptionIdUserNull() {
        var exception = catchException(() -> new Review("xpto", null, 5, "Test review"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("IdUser cannot be null");
    }

    @CsvSource({
            "0",
            "6",
            "100"
    })
    @ParameterizedTest
    void shouldReturnExceptionInvalidRating(int rating) {
        var exception = catchException(() -> new Review("xpto", "xpto2", rating, "Test review"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Rating must be between 1 and 5");
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = "null")
    @ParameterizedTest
    void shouldReturnExceptionContentNullOrEmpty(String content) {
        var exception = catchException(() -> new Review("xpto", "xpto2", 5, content));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Content cannot be null or empty");
    }

    @CsvSource(value = {
            "Test",
            "Percebemos cada vez mais" +
                    " que o entendimento das metas propostas ainda não" +
                    " demonstrou convincentemente que vai participar na" +
                    " mudança das direções preferenciais no sentido do" +
                    " progresso a certificação de metodologias que nos" +
                    " auxiliam a lidar com a determinação clara de objetivos" +
                    " nos obriga à análise das diretrizes de desenvolvimento" +
                    " para o futuro todas estas questões devidamente" +
                    " ponderadas levantam dúvidas sobre se a contínua" +
                    " expansão de nossa atividade deve passar por " +
                    " modificações indepenentemente dos procedimentos" +
                    " normalmente adotados"
    }, nullValues = "null")
    @ParameterizedTest
    void shouldReturnExceptionInvalidContent(String content) {
        var exception = catchException(() -> new Review("xpto", "xpto2", 5, content));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Content length must be between 5 and 255");
    }
}
