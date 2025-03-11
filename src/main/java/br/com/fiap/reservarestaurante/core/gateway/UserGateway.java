package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.User;

import java.util.Optional;

public interface UserGateway {
    boolean isEmailAvailable(String email);

    Optional<User> findById(String id);
    void delete(String id);

    void save(User user);
}
