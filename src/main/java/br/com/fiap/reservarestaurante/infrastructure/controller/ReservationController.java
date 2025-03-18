package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.usecase.FinalizeReservationUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurant")
public class ReservationController {

    private final FinalizeReservationUseCase finalizeReservationUseCase;

    public ReservationController(FinalizeReservationUseCase finalizeReservationUseCase) {
        this.finalizeReservationUseCase = finalizeReservationUseCase;
    }

    @PatchMapping("{idRestaurant}/reservation/{idReservation}")
    public ResponseEntity<ServiceResponse<Void>> finalizeReservation(@PathVariable String idReservation, @PathVariable String idRestaurant) {
        finalizeReservationUseCase.execute(idReservation, idRestaurant);
        return ResponseEntity.ok(ServiceResponse.build());
    }
}
