package br.com.fiap.reservarestaurante.core.dto;

public record CreateUserDTO(
        String name,
        String surname,
        int age,
        String email
) {
}
