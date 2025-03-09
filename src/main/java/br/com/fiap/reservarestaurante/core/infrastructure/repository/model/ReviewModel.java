package br.com.fiap.reservarestaurante.core.infrastructure.repository.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ReviewModel extends Model<String> {
    @NotNull
    @NotBlank
    private String idUser;
    @NotNull
    @NotBlank
    private String idRestaurant;
    private int rating;
    @NotNull
    @NotBlank
    private String content;
}
