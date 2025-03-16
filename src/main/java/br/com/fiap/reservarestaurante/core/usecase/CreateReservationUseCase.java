package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Reservation;
import br.com.fiap.reservarestaurante.core.dto.CreateReservationDTO;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;

public class CreateReservationUseCase {

    private final RestaurantGateway restaurantGateway;

    public CreateReservationUseCase(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public ReservationDTO execute(CreateReservationDTO createReservationDTO) {
        var reservation = new Reservation(
                createReservationDTO.idRestaurant(),
                createReservationDTO.idUser(),
                createReservationDTO.amountOfTables(),
                createReservationDTO.date()
        );
        var restaurant = restaurantGateway.findById(createReservationDTO.idRestaurant())
                .orElseThrow(() -> new IllegalStateException("Restaurant not found"));

        restaurant.addReservation(reservation);
        restaurantGateway.save(restaurant);

        return new ReservationDTO(createReservationDTO.idRestaurant(), createReservationDTO.idUser(), createReservationDTO.amountOfTables(), createReservationDTO.date());
    }
}
