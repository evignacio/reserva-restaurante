package br.com.fiap.reservarestaurante.core.dto;

import java.time.LocalDateTime;

public record ReservationDTO(

        String reservationId,
        String restaurantId,
        String restaurantName,
        String userId,
        int amountOfTables,
        LocalDateTime date
) {
}
