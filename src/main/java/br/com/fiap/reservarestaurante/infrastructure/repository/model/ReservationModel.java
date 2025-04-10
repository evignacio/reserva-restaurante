package br.com.fiap.reservarestaurante.infrastructure.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReservationModel extends Model<String> {
    private String idRestaurant;
    private String idUser;
    private int amountOfTables;
    private LocalDateTime date;

}
