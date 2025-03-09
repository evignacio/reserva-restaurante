package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

public class DeleteRestaurantUseCase {

    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public void execute(String id) {
        Restaurant restaurant;

        restaurant = restaurantGateway.findById(id).orElseThrow(() -> new IllegalStateException("Restaurant not found"));

        restaurantGateway.delete(restaurant.getId());
    }
}
