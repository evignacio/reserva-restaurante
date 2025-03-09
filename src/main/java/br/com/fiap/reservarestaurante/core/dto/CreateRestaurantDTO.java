package br.com.fiap.reservarestaurante.core.dto;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.WorkPeriod;

import java.util.Set;

public record CreateRestaurantDTO(String name, String categoryId, Address address, int maxCapacity, Set<WorkPeriod> workPeriods) {
}
