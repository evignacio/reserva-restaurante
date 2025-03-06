package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.gateway.ReservationGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseTest {

    @Mock
    private ReservationGateway reservationGateway;

    @InjectMocks
    private CreateReservationUseCase createReservationUseCase;

    @Test
    void shouldCreateReservation() {
        var idRestaurant = "1";
        var idUser = "2";
        var numberOfClients = 4;
        var date = LocalDateTime.now();
        var reservationReq = new ReservationDTO(idRestaurant, idUser, numberOfClients, date);

        when(reservationGateway.isLessThanTwoDaysBefore(date)).thenReturn(false);
        when(reservationGateway.isDateAvailable(date, idRestaurant, numberOfClients)).thenReturn(true);

        var result = assertDoesNotThrow(() -> createReservationUseCase.execute(reservationReq));
        assertThat(result).isNotNull();
        assertThat(result.date()).isEqualTo(date);
        assertThat(result.idRestaurant()).isEqualTo(idRestaurant);
        assertThat(result.idUser()).isEqualTo(idUser);
        assertThat(result.numberOfClients()).isEqualTo(numberOfClients);

    }

    @Test
    void shouldReturnExceptionDateIsTooClose() {
        var idRestaurant = "1";
        var idUser = "2";
        var numberOfClients = 4;
        var date = LocalDateTime.now();
        var reservationReq = new ReservationDTO(idRestaurant, idUser, numberOfClients, date);

        when(reservationGateway.isLessThanTwoDaysBefore(date)).thenReturn(true);

        var exception = catchThrowable(() -> createReservationUseCase.execute(reservationReq));
        assertThat(exception).isInstanceOf(IllegalStateException.class);
        assertThat(exception.getMessage()).isEqualTo("Reservation date is greater than two days in advance");
    }

    @Test
    void shouldReturnExceptionDateNotAvaiable() {
        var idRestaurant = "1";
        var idUser = "2";
        var numberOfClients = 4;
        var date = LocalDateTime.now();
        var reservationReq = new ReservationDTO(idRestaurant, idUser, numberOfClients, date);

        when(reservationGateway.isLessThanTwoDaysBefore(date)).thenReturn(false);
        when(reservationGateway.isDateAvailable(date, idRestaurant, numberOfClients)).thenReturn(false);

        var exception = catchThrowable(() -> createReservationUseCase.execute(reservationReq));
        assertThat(exception).isInstanceOf(IllegalStateException.class);
        assertThat(exception.getMessage()).isEqualTo("Reservation date not available");
    }

}
