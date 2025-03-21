package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.dto.AddressDTO;
import br.com.fiap.reservarestaurante.core.dto.CreateRestaurantDTO;
import br.com.fiap.reservarestaurante.core.dto.RestaurantDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateRestaurantUseCase;
import br.com.fiap.reservarestaurante.core.usecase.DeleteRestaurantUseCase;
import br.com.fiap.reservarestaurante.core.usecase.ListRestaurantsUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private final ListRestaurantsUseCase listRestaurantsUseCase;
    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final DeleteRestaurantUseCase deleteRestaurantUseCase;

    public RestaurantController(ListRestaurantsUseCase listRestaurantsUseCase, CreateRestaurantUseCase createRestaurantUseCase, DeleteRestaurantUseCase deleteRestaurantUseCase) {
        this.listRestaurantsUseCase = listRestaurantsUseCase;
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.deleteRestaurantUseCase = deleteRestaurantUseCase;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<Set<RestaurantDTO>>> listRestaurants(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String categoryName,
            @Parameter(example = "ewogICAgImNpdHkiOiAiU2FudG9zIiwKICAgICJzdGF0ZSI6ICJTUCIsCiAgICAiY291bnRyeSI6ICJCcmF6aWwiCn0") @RequestParam(required = false, name = "address") String addressBase64,
            @Parameter(example = "2025-03-17T12:25:00") @RequestParam(required = false) LocalDateTime date) {
        AddressDTO address = null;
        if (addressBase64 != null) {
            try {
                var addresJsonValue = Base64.getDecoder().decode(addressBase64);
                ObjectMapper objectMapper = new ObjectMapper();
                address = objectMapper.readValue(addresJsonValue, AddressDTO.class);
                if (address.country().isBlank() || address.city().isBlank() || address.state().isBlank())
                    throw new IllegalArgumentException("Address is not complete");
            } catch (Exception e) {
                throw new IllegalArgumentException("Was not possible to parse the address");
            }
        }
        var restaurants = listRestaurantsUseCase.execute(name, categoryName, address, date);
        return ResponseEntity.ok(ServiceResponse.build(restaurants));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<Restaurant>> createRestaurant(@RequestBody CreateRestaurantDTO createRestaurantDTO) {
        var restaurant = createRestaurantUseCase.execute(createRestaurantDTO);
        return ResponseEntity.ok(ServiceResponse.build(restaurant));
    }

    @DeleteMapping("{idRestaurant}")
    public ResponseEntity<ServiceResponse<Void>> deleteRestaurant(@PathVariable String idRestaurant) {
        deleteRestaurantUseCase.execute(idRestaurant);
        return ResponseEntity.ok(ServiceResponse.build());
    }
}
