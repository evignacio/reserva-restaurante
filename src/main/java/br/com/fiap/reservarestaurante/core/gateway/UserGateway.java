package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.User;

public interface UserGateway {
    boolean isEmailAvailable(String email);

    void save(User user);
}
