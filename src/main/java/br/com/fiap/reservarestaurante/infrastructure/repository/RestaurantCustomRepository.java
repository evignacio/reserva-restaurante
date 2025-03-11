package br.com.fiap.reservarestaurante.infrastructure.repository;

import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;

import java.util.Set;

public interface RestaurantCustomRepository {
    Set<RestaurantModel> findAll(String name, String categoryId, AddressModel address);
}
