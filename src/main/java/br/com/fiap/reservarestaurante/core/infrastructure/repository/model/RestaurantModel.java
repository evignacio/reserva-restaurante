package br.com.fiap.reservarestaurante.core.infrastructure.repository.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RestaurantModel extends Model<String> {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private AddressModel address;
    private int maxCapacity;
    @NotNull
    private CategoryModel category;
    @NotNull
    @NotEmpty
    private Set<WorkPeriodModel> workPeriods;
    private Set<ReservationModel> reservations;
    private Set<ReviewModel> reviews;
}
