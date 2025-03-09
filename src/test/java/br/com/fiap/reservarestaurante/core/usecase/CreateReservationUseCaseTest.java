package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Reservation;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.gateway.ReservationGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        var result = assertDoesNotThrow(() -> createReservationUseCase.execute(reservationReq));
        verify(reservationGateway, times(1)).save(any(Reservation.class));
        assertThat(result).isNotNull();
        assertThat(result.date()).isEqualTo(date);
        assertThat(result.idRestaurant()).isEqualTo(idRestaurant);
        assertThat(result.idUser()).isEqualTo(idUser);
        assertThat(result.numberOfClients()).isEqualTo(numberOfClients);

    }
}
