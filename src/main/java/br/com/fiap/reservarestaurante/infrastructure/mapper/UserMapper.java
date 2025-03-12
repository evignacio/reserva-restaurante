package br.com.fiap.reservarestaurante.infrastructure.mapper;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.UserModel;

public abstract class UserMapper {

    private UserMapper() {
    }

    public static User toDomain(UserModel userModel) {
        return new User(userModel.getId(),
                userModel.getName(),
                userModel.getSurname(),
                userModel.getAge(),
                userModel.getEmail());
    }

    public static UserModel toModel(User user) {
        return UserModel.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail()).build();
    }
}
