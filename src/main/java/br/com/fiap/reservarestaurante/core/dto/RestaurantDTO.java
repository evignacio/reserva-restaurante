package br.com.fiap.reservarestaurante.core.dto;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Review;

import java.util.Set;

public record RestaurantDTO(String id, String name, String category, Address address, int amountOfVaccanciesAvailable,
                            Set<Review> reviews, boolean open) {
}
