package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Restaurant;

import java.util.Optional;
import java.util.Set;

public interface RestaurantGateway {
    boolean nameIsAvailable(String name);

    void save(Restaurant restaurant);

    Set<Restaurant> find(String name, String categoryId, Address address);

    Optional<Restaurant> findById(String id);
}
