package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.dto.AddressDTO;
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

    public Set<RestaurantDTO> execute(String name, String categoryName, AddressDTO address, LocalDateTime date) {
        if (date == null)
            date = LocalDateTime.now();
        else if (date.isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Date cannot be in the past");

        LocalDateTime finalDate = date;
        return restaurantGateway.find(name, categoryName, address)
                .stream()
                .map(restaurant -> new RestaurantDTO(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getCategory().getName(),
                        restaurant.getAddress(),
                        restaurant.getAmountOfReservationsAvailableForDay(finalDate),
                        restaurant.getReviews(), restaurant.isOpen(finalDate)))
                .collect(Collectors.toSet());
    }
}
