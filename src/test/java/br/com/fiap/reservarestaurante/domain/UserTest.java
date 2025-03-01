package br.com.fiap.reservarestaurante.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserTest {

    @Test
    void shouldCreateUser() {
        var result = assertDoesNotThrow(() -> new User("Evandro", "Pastor", 49));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Evandro");
        assertThat(result.getSurname()).isEqualTo("Pastor");
        assertThat(result.getAge()).isEqualTo(49);
    }

    @Test
    void shuldReturnFullName() {
        var result = assertDoesNotThrow(() -> new User("Evandro", "Pastor", 49));
        assertThat(result.getFullName()).isEqualTo("Evandro Pastor");
    }

    @Test
    void shouldReturnExceptionFistNameNull() {
        var exception = catchThrowable(() -> new User(null, "Pastor", 49));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Name cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionFistNameEmpty() {
        var exception = catchThrowable(() -> new User("", "Pastor", 49));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Name cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionFistNameInvalid() {
        var exception = catchThrowable(() -> new User("EV", "Pastor", 49));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Name must be at least 3 characters");
    }


    @Test
    void shouldReturnExceptionSurnameNameNull() {
        var exception = catchThrowable(() -> new User("Evandro", null, 49));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Surname cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionSurnameNameEmpty() {
        var exception = catchThrowable(() -> new User("Evandro", "", 49));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Surname cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionSurnameNameInvalid() {
        var exception = catchThrowable(() -> new User("Evandro", "Pa", 49));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Surname must be at least 3 characters");
    }


    @Test
    void shouldReturnExceptionAgeUnder18() {
        var exception = catchThrowable(() -> new User("Evandro", "Pastor", 17));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Age must be at least 18");
    }

    @Test
    void shouldReturnExceptionAgeOver100() {
        var exception = catchThrowable(() -> new User("Evandro", "Pastor", 400));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Age must be at most 100");
    }

}
