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
        assertThat(result.getAmountOfTables()).isEqualTo(5);
        assertThat(result.getDate()).isNotNull();
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionIdRestaurantNullOrEmpty(String idRestaurant) {
        var exception = catchThrowable(() -> new Reservation(idRestaurant, "userId", 5, LocalDateTime.now()));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("IdRestaurant cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionIdUserNull() {
        var exception = catchThrowable(() -> new Reservation("restaurantId", null, 5, LocalDateTime.now()));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("IdUser cannot be null");
    }

    @CsvSource({
            "0",
            "6"
    })
    @ParameterizedTest
    void shouldReturnExceptionNumnerOfClientsInvalid(int numnerOfClients) {
        var exception = catchThrowable(() -> new Reservation("restaurantId", "userId", numnerOfClients, LocalDateTime.now()));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AmountOfTables must be between 1 and 5");
    }

    @Test
    void shouldReturnExceptionDateNull() {
        var exception = catchThrowable(() -> new Reservation("restaurantId", "userId", 5, null));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Date cannot be null");
    }
}
