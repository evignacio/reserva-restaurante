package br.com.fiap.reservarestaurante.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CategoryTest {

    @Test
    void shouldCreateCategory() {
        var result = assertDoesNotThrow(() -> new Category("Japonesa"));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Japonesa");
    }

  @CsvSource(value = {
          "null",
          "''"
  }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionNameNullOrEmpty(String name) {
        var exception =  catchThrowable(() -> new Category(name));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Name cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionNameInvalid() {
        var exception =  catchThrowable(() -> new Category("JA"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Name must be at least 3 characters");
    }
}
