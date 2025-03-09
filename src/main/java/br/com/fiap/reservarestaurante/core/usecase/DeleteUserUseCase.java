package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;

public class DeleteUserUseCase {

    private final UserGateway userGateway;

    public DeleteUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void execute(String id) {
        User user;
        var optUser = userGateway.findById(id);
        user = optUser.orElseThrow(() -> new IllegalStateException("User not found."));
        userGateway.delete(user.getId());
    }
}
