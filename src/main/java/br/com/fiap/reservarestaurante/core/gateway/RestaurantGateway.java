package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;

public interface RestaurantGateway {
    boolean nameIsAvailable(String name);
    void save(Restaurant restaurant);
}
