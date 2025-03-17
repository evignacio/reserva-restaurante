package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.CreateReservationDTO;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateReservationUseCase;
import br.com.fiap.reservarestaurante.core.usecase.FindUserReservationsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController("reservationController")
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final FindUserReservationsUseCase findUserReservationsUseCase;

    public ReservationController(CreateReservationUseCase createReservationUseCase, FindUserReservationsUseCase findUserReservationsUseCase) {
        this.createReservationUseCase = createReservationUseCase;
        this.findUserReservationsUseCase = findUserReservationsUseCase;
    }

    @PostMapping()
    ResponseEntity<ReservationDTO> reserve(CreateReservationDTO createReservationDTO) {
        var reservation = createReservationUseCase.execute(createReservationDTO);
        var uri = URI.create("/reservations/" + reservation.reservationId());
        return ResponseEntity.created(uri).body(reservation);
    }

    @GetMapping("/reservations/users/{userId}")
    ResponseEntity<Set<ReservationDTO>> findUserReservations(@PathVariable UUID userId) {
        var reservations = findUserReservationsUseCase.execute(userId);
        return ResponseEntity.ok(reservations);
    }
}
