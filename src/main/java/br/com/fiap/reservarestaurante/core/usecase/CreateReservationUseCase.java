package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Reservation;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.gateway.ReservationGateway;

public class CreateReservationUseCase {

    private final ReservationGateway reservationGateway;

    public CreateReservationUseCase(ReservationGateway reservationGateway) {
        this.reservationGateway = reservationGateway;
    }

    public ReservationDTO execute(ReservationDTO reservationDTO) {
        if (reservationGateway.isLessThanTwoDaysBefore(reservationDTO.date()))
            throw new IllegalStateException("Reservation date is greater than two days in advance");

        if (!reservationGateway.isDateAvailable(reservationDTO.date(), reservationDTO.idRestaurant(), reservationDTO.numberOfClients()))
            throw new IllegalStateException("Reservation date not available");
        var reservation = new Reservation(reservationDTO.idRestaurant(), reservationDTO.idUser(), reservationDTO.numberOfClients(), reservationDTO.date());
        reservationGateway.save(reservation);
        return reservationDTO;
    }
}
