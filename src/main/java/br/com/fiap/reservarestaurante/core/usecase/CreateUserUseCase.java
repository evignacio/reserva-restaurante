package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.core.dto.CreateUserDTO;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(CreateUserDTO userDto) {
        if (!userGateway.isEmailAvailable(userDto.email()))
            throw new IllegalStateException("User email not available");

        var user = new User(userDto.name(), userDto.surname(), userDto.age(), userDto.email());

        userGateway.save(user);
        return user;
    }
}
