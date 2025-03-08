package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.User;

import java.util.Optional;

public interface UserGateway {
    Optional<User> findById(String id);
}
