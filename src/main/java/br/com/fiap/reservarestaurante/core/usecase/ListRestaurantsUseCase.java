package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.dto.RestaurantDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ListRestaurantsUseCase {

    private final RestaurantGateway restaurantGateway;

    public ListRestaurantsUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public Set<RestaurantDTO> execute(String name, String categoryName, Address address, LocalDateTime date) {
        return restaurantGateway.find(name, categoryName, address)
                .stream()
                .map(restaurant -> new RestaurantDTO(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getCategory().getName(),
                        restaurant.getAddress(),
                        restaurant.getAmountOfReservationsAvailableForDay(date),
                        restaurant.getReviews(), restaurant.isOpen(date)))
                .collect(Collectors.toSet());
    }
}
