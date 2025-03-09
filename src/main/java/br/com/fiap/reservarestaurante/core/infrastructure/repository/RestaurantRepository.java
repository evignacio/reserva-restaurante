package br.com.fiap.reservarestaurante.core.infrastructure.repository;

import br.com.fiap.reservarestaurante.core.infrastructure.repository.model.RestaurantModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<RestaurantModel, String> {
}
