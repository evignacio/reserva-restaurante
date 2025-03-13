package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.dto.UserDTO;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;

import java.util.UUID;

public class FindUserUseCase {

    private final UserGateway userGateway;

    public FindUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserDTO execute(String email) {

        var user = userGateway.findByEmail(email).orElseThrow(() -> new IllegalStateException("User not found"));

        var id = UUID.fromString(user.getId());

        return new UserDTO(id, user.getName(), user.getEmail());
    }
}
