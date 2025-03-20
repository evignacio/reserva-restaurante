package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

import java.util.HashSet;
import java.util.Set;

public class FindUserReservationsUseCase {

    private final RestaurantGateway restaurantGateway;

    public FindUserReservationsUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public Set<ReservationDTO> execute(String userId) {

        Set<ReservationDTO> reservationDTOS = new HashSet<>();

        var restaurants = restaurantGateway.findUserReservations(userId);
        restaurants.forEach(restaurant ->
                restaurant.getReservations()
                        .forEach(reservation ->
                                reservationDTOS
                                        .add(new ReservationDTO(
                                                reservation.getId(),
                                                restaurant.getId(),
                                                restaurant.getName(),
                                                reservation.getIdUser(),
                                                reservation.getAmountOfTables(),
                                                reservation.getDate())))
        );
        return reservationDTOS;
    }
}
