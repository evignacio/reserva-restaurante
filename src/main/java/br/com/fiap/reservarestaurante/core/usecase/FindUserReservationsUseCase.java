package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FindUserReservationsUseCase {

    private final RestaurantGateway restaurantGateway;

    public FindUserReservationsUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public Set<ReservationDTO> execute(UUID userId) {

        Set<ReservationDTO> reservationDTOS = new HashSet<>();

        var restaurants = restaurantGateway.findUserFutureReservations(userId);
        restaurants.forEach(restaurant ->
                restaurant.getReservations()
                        .forEach(reservation ->
                                reservationDTOS
                                        .add(new ReservationDTO(
                                                null,
                                                restaurant.getId(),
                                                restaurant.getName(),
                                                reservation.getIdUser(),
                                                reservation.getAmountOfTables(),
                                                reservation.getDate())))
        );
        return reservationDTOS;
    }
}
