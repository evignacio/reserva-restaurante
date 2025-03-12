package br.com.fiap.reservarestaurante.infrastructure.repository;

import br.com.fiap.reservarestaurante.infrastructure.repository.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
    boolean existsByEmail(String email);
}
