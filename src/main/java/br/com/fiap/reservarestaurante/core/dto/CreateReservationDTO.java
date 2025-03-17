package br.com.fiap.reservarestaurante.core.dto;

import java.time.LocalDateTime;

public record CreateReservationDTO(

        String idRestaurant,
        String idUser,
        int amountOfTables,
        LocalDateTime date
) {
}
