package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.RestaurantDTO;
import br.com.fiap.reservarestaurante.core.usecase.ListRestaurantsUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    private final ListRestaurantsUseCase listRestaurantsUseCase;

    public RestaurantController(ListRestaurantsUseCase listRestaurantsUseCase) {
        this.listRestaurantsUseCase = listRestaurantsUseCase;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<Set<RestaurantDTO>>> listRestaurants(@RequestParam(required = false) String name, @RequestParam(required = false) String categoryId, @RequestParam(required = false) LocalDateTime date) {
        var restaurants = listRestaurantsUseCase.execute(name, categoryId, null, date);
        return ResponseEntity.ok(ServiceResponse.build(restaurants));
    }
}
