package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(User user) {
        if (!userGateway.isEmailAvailable(user.getEmail()))
            throw new IllegalStateException("User email not available");

        userGateway.save(user);
        return user;
    }
}
