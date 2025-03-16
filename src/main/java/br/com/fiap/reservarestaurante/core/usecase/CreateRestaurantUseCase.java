package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.dto.CreateRestaurantDTO;
import br.com.fiap.reservarestaurante.core.gateway.CategoryGateway;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

public class CreateRestaurantUseCase {

    private final RestaurantGateway restaurantGateway;
    private final CategoryGateway categoryGateway;

    public CreateRestaurantUseCase(RestaurantGateway restaurantGateway, CategoryGateway categoryGateway) {
        this.restaurantGateway = restaurantGateway;
        this.categoryGateway = categoryGateway;
    }

    public Restaurant execute(CreateRestaurantDTO restaurantDTO) {
        if (!restaurantGateway.nameIsAvailable(restaurantDTO.name()))
            throw new IllegalStateException("Name restaurant not available");

        var category = categoryGateway.findByName(restaurantDTO.categoryName())
                .orElseThrow(() -> new IllegalStateException("Category not found"));

        var restaurant = new Restaurant(
                restaurantDTO.name(),
                restaurantDTO.address(),
                restaurantDTO.maxCapacity(),
                category,
                restaurantDTO.workPeriods());

        restaurantGateway.save(restaurant);
        return restaurant;
    }
}
