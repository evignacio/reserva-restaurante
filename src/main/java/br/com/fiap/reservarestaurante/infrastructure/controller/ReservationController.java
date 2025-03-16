package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.CreateReservationDTO;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateReservationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("reservationController")
@RequestMapping(value = "/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;

    public ReservationController(CreateReservationUseCase createReservationUseCase) {
        this.createReservationUseCase = createReservationUseCase;
    }

    @PostMapping()
    ResponseEntity<ReservationDTO> reserve(CreateReservationDTO createReservationDTO) {
        var reservation = createReservationUseCase.execute(createReservationDTO);
        var uri = URI.create("/reservations/" + reservation.idUser());
        return ResponseEntity.created(uri).body(reservation);
    }
}
