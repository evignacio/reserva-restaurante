package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Reservation;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

public class CreateReservationUseCase {

    private final RestaurantGateway restaurantGateway;

    public CreateReservationUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public ReservationDTO execute(ReservationDTO reservationDTO) {
        var reservation = new Reservation(
                reservationDTO.idRestaurant(),
                reservationDTO.idUser(),
                reservationDTO.amountOfTables(),
                reservationDTO.date()
        );
        var restaurant = restaurantGateway.findById(reservationDTO.idRestaurant())
                .orElseThrow(() -> new IllegalStateException("Restaurant not found"));

        restaurant.addReservation(reservation);
        restaurantGateway.save(restaurant);
        return reservationDTO;
    }
}
