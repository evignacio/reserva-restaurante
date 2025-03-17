package br.com.fiap.reservarestaurante.infrastructure.repository;

import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface RestaurantCustomRepository {
    Set<RestaurantModel> findAll(String name, String categoryId, AddressModel address);

    Set<RestaurantModel> findWithUserFutureReservations(UUID userId);
}
