package br.com.fiap.reservarestaurante.core.dto;

import java.time.LocalDateTime;

public record ReservationDTO(
        String idRestaurant,
        String idUser,
        int numberOfClients,
        LocalDateTime date
) {
}
