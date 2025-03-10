package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

public class FinalizeReservationUseCase {

    private final RestaurantGateway restaurantGateway;

    public FinalizeReservationUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public void execute(String idReservation, String idRestaurant) {
        var restaurant = restaurantGateway.findById(idRestaurant)
                .orElseThrow(() -> new IllegalStateException("Restaurant not found"));

        boolean reservationFound = restaurant.getReservations().removeIf(r -> r.getId().equals(idReservation));

        if (!reservationFound)
            throw new IllegalStateException("Reservation not found");

        this.restaurantGateway.save(restaurant);
    }
}
