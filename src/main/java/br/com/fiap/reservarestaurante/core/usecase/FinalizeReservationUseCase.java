package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

public class FinalizeReservationUseCase {

    private final RestaurantGateway restaurantGateway;

    public FinalizeReservationUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public void execute(String idReservation, String idRestaurant) {
        var restaurantOpt = restaurantGateway.findById(idRestaurant);

        if (restaurantOpt.isEmpty())
            throw new IllegalStateException("Restaurant not found");

        var restaurant = restaurantOpt.get();
        boolean reservationFound = restaurant.getReservations().removeIf(r -> r.getId().equals(idReservation));

        if (!reservationFound)
            throw new IllegalStateException("Reservation not found");

        this.restaurantGateway.save(restaurant);
    }
}
