package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.dto.AddressDTO;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RestaurantGateway {
    boolean nameIsAvailable(String name);

    void save(Restaurant restaurant);

    Set<Restaurant> find(String name, String categoryName, AddressDTO address);

    Optional<Restaurant> findById(String id);

    Set<Restaurant> findUserFutureReservations(UUID userId);

    void delete(String id);
}
