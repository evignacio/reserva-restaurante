package br.com.fiap.reservarestaurante.core.gateway;

import br.com.fiap.reservarestaurante.core.domain.Reservation;

public interface ReservationGateway {

    void save(Reservation reservation);
}
