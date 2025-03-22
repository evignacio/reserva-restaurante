package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.CreateReservationDTO;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateReservationUseCase;
import br.com.fiap.reservarestaurante.core.usecase.FinalizeReservationUseCase;
import br.com.fiap.reservarestaurante.core.usecase.FindUserReservationsUseCase;
import br.com.fiap.reservarestaurante.infrastructure.controller.response.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final FinalizeReservationUseCase finalizeReservationUseCase;
    private final FindUserReservationsUseCase findUserReservationsUseCase;

    public ReservationController(CreateReservationUseCase createReservationUseCase, FinalizeReservationUseCase finalizeReservationUseCase, FindUserReservationsUseCase findUserReservationsUseCase) {
        this.createReservationUseCase = createReservationUseCase;
        this.finalizeReservationUseCase = finalizeReservationUseCase;
        this.findUserReservationsUseCase = findUserReservationsUseCase;
    }

    @PostMapping("reservations")
    public ResponseEntity<ServiceResponse<ReservationDTO>> createReservation(@RequestBody CreateReservationDTO reservationDTO) {
        var reservation = createReservationUseCase.execute(reservationDTO);
        return ResponseEntity.ok(ServiceResponse.build(reservation));
    }

    @PatchMapping("reservations/{idReservation}/restaurants/{idRestaurant}")
    public ResponseEntity<ServiceResponse<Void>> finalizeReservation(@PathVariable String idRestaurant, @PathVariable String idReservation) {
        finalizeReservationUseCase.execute(idReservation, idRestaurant);
        return ResponseEntity.ok(ServiceResponse.build());
    }

    @GetMapping("reservations/users/{idUser}")
    public ResponseEntity<ServiceResponse<Set<ReservationDTO>>> findUserReservation(@PathVariable String idUser) {
        return ResponseEntity.ok(ServiceResponse.build(findUserReservationsUseCase.execute(idUser)));
    }
}
