package br.com.fiap.reservarestaurante.infrastructure.repository;

import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<RestaurantModel, String>, RestaurantCustomRepository {
    boolean existsByName(String name);
}
