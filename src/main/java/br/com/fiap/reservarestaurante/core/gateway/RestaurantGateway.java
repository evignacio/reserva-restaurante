package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.dto.AddressDTO;

import java.util.Optional;
import java.util.Set;

public interface RestaurantGateway {
    boolean nameIsAvailable(String name);

    void save(Restaurant restaurant);

    Set<Restaurant> find(String name, String categoryName, AddressDTO address);

    Optional<Restaurant> findById(String id);

    Set<Restaurant> findUserReservations(String userId);

    void delete(String id);
}
