package br.com.fiap.reservarestaurante.core.dto;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String email
) {
}
