package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.*;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinalizeReservationUseCaseTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    @InjectMocks
    private FinalizeReservationUseCase finalizeReservationUseCase;

    @Test
    void shouldFinalizeReservation() {
        var address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        var workPeriods = new HashSet<WorkPeriod>();
        var startHour = 10;
        var endHour = 22;
        workPeriods.add(new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.TUESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.WEDNESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.THURSDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.FRIDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SATURDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SUNDAY, startHour, endHour));
        var reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().withHour(12));
        var restaurant = new Restaurant("Restaurant Name", address, 50, new Category("Italian"), workPeriods);
        restaurant.addReservation(reservation);

        when(restaurantGateway.findById("restaurantId")).thenReturn(Optional.of(restaurant));
        doNothing().when(restaurantGateway).save(restaurant);

        finalizeReservationUseCase.execute(reservation.getId(), "restaurantId");

        assertThat(restaurant.getReservations()).isEmpty();
    }

    @Test
    void shouldReturnExceptionRestaurantNotFound() {
        var reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().withHour(12));

        when(restaurantGateway.findById(reservation.getIdRestaurant())).thenReturn(Optional.empty());
        var exception = catchException(() -> finalizeReservationUseCase.execute(reservation.getId(), "restaurantId"));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Restaurant not found");
    }

    @Test
    void shouldReturnExceptionReservationNotFound() {
        var address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        var workPeriods = new HashSet<WorkPeriod>();
        var startHour = 10;
        var endHour = 22;
        workPeriods.add(new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.TUESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.WEDNESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.THURSDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.FRIDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SATURDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SUNDAY, startHour, endHour));
        var restaurant = new Restaurant("Restaurant Name", address, 50, new Category("Italian"), workPeriods);

        when(restaurantGateway.findById("restaurantId")).thenReturn(Optional.of(restaurant));

        var exception = catchException(() -> finalizeReservationUseCase.execute("reservationId", "restaurantId"));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Reservation not found");
    }

}
