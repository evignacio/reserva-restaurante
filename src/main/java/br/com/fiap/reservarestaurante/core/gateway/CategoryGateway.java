package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.Category;

import java.util.Optional;

public interface CategoryGateway {
    Optional<Category> findById(String id);
}
