package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Category;
import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.domain.WorkPeriod;
import br.com.fiap.reservarestaurante.core.dto.AddressDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListRestaurantsUseCaseTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    @InjectMocks
    private ListRestaurantsUseCase listRestaurantsUseCase;

    @Test
    void shloudListRestaurants() {
        var address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        var addressDTO = new AddressDTO(address.getCountry(), address.getCity(), address.getState());
        var workPeriod = new WorkPeriod(DayOfWeek.WEDNESDAY, 10, 22);
        var restaurant = new Restaurant("Restaurant Name", address, 50, new Category("Italian"), Set.of(workPeriod));
        when(restaurantGateway.find("Restaurant Name", "categoryId", addressDTO)).thenReturn(Set.of(restaurant));

        var result = listRestaurantsUseCase.execute("Restaurant Name", "categoryId", addressDTO, LocalDateTime.now().plusDays(1));
        assertThat(result).hasSize(1);
    }


    @Test
    void shouldUseCurrentDateWhenDateIsNull() {
        var address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        var addressDTO = new AddressDTO(address.getCountry(), address.getCity(), address.getState());
        when(restaurantGateway.find("Restaurant Name", "categoryId", addressDTO)).thenReturn(Set.of());

        var result = listRestaurantsUseCase.execute("Restaurant Name", "categoryId", addressDTO, null);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldThrowExceptionWhenDateIsInThePast() {
        var address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        var addressDTO = new AddressDTO(address.getCountry(), address.getCity(), address.getState());
        var pastDate = LocalDateTime.now().minusDays(1);

        var exception = catchThrowable(() -> listRestaurantsUseCase.execute("Restaurant Name", "categoryId", addressDTO, pastDate));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Date cannot be in the past");
    }
}
