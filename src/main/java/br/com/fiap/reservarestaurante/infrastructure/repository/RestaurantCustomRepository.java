package br.com.fiap.reservarestaurante.infrastructure.repository;

import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RestaurantCustomRepository {
    Set<RestaurantModel> findAll(String name, String categoryName, AddressModel address);
    Set<RestaurantModel> findWithUserReservations(String userId);

}
