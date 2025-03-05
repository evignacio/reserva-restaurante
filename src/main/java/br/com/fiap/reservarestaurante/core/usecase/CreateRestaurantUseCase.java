package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.gateway.CategoryGateway;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

public class CreateRestaurantUseCase {

    private final RestaurantGateway restaurantGateway;
    private final CategoryGateway categoryGateway;

    public CreateRestaurantUseCase(RestaurantGateway restaurantGateway, CategoryGateway categoryGateway) {
        this.restaurantGateway = restaurantGateway;
        this.categoryGateway = categoryGateway;
    }

    public Restaurant execute(Restaurant restaurant) {
        if (!restaurantGateway.nameIsAvailable(restaurant.getName()))
            throw new IllegalStateException("Name restaurant not available");

        if (categoryGateway.findById(restaurant.getCategory().getId()).isEmpty())
            throw new IllegalStateException("Category not found");

        restaurantGateway.save(restaurant);
        return restaurant;
    }
}
