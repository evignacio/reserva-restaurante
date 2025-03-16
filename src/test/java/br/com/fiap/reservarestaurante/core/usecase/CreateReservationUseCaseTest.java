package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.*;
import br.com.fiap.reservarestaurante.core.dto.CreateReservationDTO;
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
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    @InjectMocks
    private CreateReservationUseCase createReservationUseCase;

    @Test
    void shouldCreateReservation() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Set<WorkPeriod> workPeriods = new HashSet<>();
        var startHour = 10;
        var endHour = 22;
        workPeriods.add(new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.TUESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.WEDNESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.THURSDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.FRIDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SATURDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SUNDAY, startHour, endHour));
        var resturant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);

        var dataReserva = LocalDateTime.now().withHour(12);
        var amountOfTables = 2;
        var reservationReq = new CreateReservationDTO(resturant.getId(), "idUser", amountOfTables, dataReserva);

        when(restaurantGateway.findById(resturant.getId())).thenReturn(Optional.of(resturant));

        var result = assertDoesNotThrow(() -> createReservationUseCase.execute(reservationReq));
        verify(restaurantGateway, times(1)).save(any(Restaurant.class));
        assertThat(result).isNotNull();
        assertThat(result.date()).isEqualTo(dataReserva);
        assertThat(result.idRestaurant()).isEqualTo(reservationReq.idRestaurant());
        assertThat(result.idUser()).isEqualTo(reservationReq.idUser());
        assertThat(result.amountOfTables()).isEqualTo(amountOfTables);

    }

    @Test
    void shouldReturnExceptionRestaurantNotFound() {
        var dataReserva = LocalDateTime.now().withHour(12);
        var amountOfTables = 2;
        var reservationReq = new CreateReservationDTO("restaurantId", "idUser", amountOfTables, dataReserva);

        when(restaurantGateway.findById(reservationReq.idRestaurant())).thenReturn(Optional.empty());
        var exception = assertThrows(IllegalStateException.class, () -> createReservationUseCase.execute(reservationReq));

        assertThat(exception).isInstanceOf(IllegalStateException.class)
                .hasMessage("Restaurant not found");
    }
}
