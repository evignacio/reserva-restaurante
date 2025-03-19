package br.com.fiap.reservarestaurante.infrastructure.repository.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressModel {
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String state;
    @NotNull
    @NotBlank
    private String country;
    @NotNull
    @NotBlank
    private String street;
    private int number;
    @NotNull
    @NotBlank
    private String zipCode;

    public AddressModel() {
        //default
    }
}
