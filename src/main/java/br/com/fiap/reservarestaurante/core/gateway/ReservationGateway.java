package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.Reservation;

import java.time.LocalDateTime;

public interface ReservationGateway {

    boolean isLessThanTwoDaysBefore(LocalDateTime reservationDate);

    boolean isDateAvailable(LocalDateTime reservationDate, String restaurantId, int numberOfClients);

    void save(Reservation user);
}
