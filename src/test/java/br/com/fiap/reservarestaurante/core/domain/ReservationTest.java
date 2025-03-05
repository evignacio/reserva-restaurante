package br.com.fiap.reservarestaurante.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReservationTest {

    @Test
    void shouldCreateReservation() {
        var result = assertDoesNotThrow(() -> new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));
        assertThat(result).isNotNull();
        assertThat(result.getIdRestaurant()).isEqualTo("restaurantId");
        assertThat(result.getIdUser()).isEqualTo("userId");
        assertThat(result.getNumberOfClients()).isEqualTo(5);
        assertThat(result.getDate()).isNotNull();
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionIdRestaurantNullOrEmpty(String idRestaurant) {
        var exception = catchThrowable(() -> new Reservation(idRestaurant, "userId", 5, LocalDateTime.now()));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("IdRestaurant cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionIdUserNull() {
        var exception = catchThrowable(() -> new Reservation("restaurantId", null, 5, LocalDateTime.now()));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("IdUser cannot be null");
    }

    @CsvSource({
            "0",
            "11"
    })
    @ParameterizedTest
    void shouldReturnExceptionNumnerOfClientsInvalid(int numnerOfClients) {
        var exception = catchThrowable(() -> new Reservation("restaurantId", "userId", numnerOfClients, LocalDateTime.now()));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("NumnerOfClients must be between 1 and 10");
    }

    @Test
    void shouldReturnExceptionDateNull() {
        var exception = catchThrowable(() -> new Reservation("restaurantId", "userId", 5, null));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Date cannot be null");
    }
}
