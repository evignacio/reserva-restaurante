package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.dto.CreateRestaurantDTO;
import br.com.fiap.reservarestaurante.core.dto.RestaurantDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateRestaurantUseCase;
import br.com.fiap.reservarestaurante.core.usecase.ListRestaurantsUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    private final ListRestaurantsUseCase listRestaurantsUseCase;
    private final CreateRestaurantUseCase createRestaurantUseCase;

    public RestaurantController(ListRestaurantsUseCase listRestaurantsUseCase, CreateRestaurantUseCase createRestaurantUseCase) {
        this.listRestaurantsUseCase = listRestaurantsUseCase;
        this.createRestaurantUseCase = createRestaurantUseCase;
    }

    //TODO: ajustar a consulta por data
    @GetMapping
    public ResponseEntity<ServiceResponse<Set<RestaurantDTO>>> listRestaurants(@RequestParam(required = false) String name, @RequestParam(required = false) String categoryName, @RequestParam(required = false) LocalDateTime date) {
        var restaurants = listRestaurantsUseCase.execute(name, categoryName, null, date);
        return ResponseEntity.ok(ServiceResponse.build(restaurants));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<Restaurant>> createRestaurant(@RequestBody CreateRestaurantDTO createRestaurantDTO) {
        var restaurant = createRestaurantUseCase.execute(createRestaurantDTO);
        return ResponseEntity.ok(ServiceResponse.build(restaurant));
    }
}
