package br.com.fiap.reservarestaurante.core.infrastructure.gateway;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Category;
import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.domain.WorkPeriod;
import br.com.fiap.reservarestaurante.core.infrastructure.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantGatewayImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantGatewayImpl restaurantGateway;

    @Test
    void shloudResturnTrueWhenNameIsAvailable() {
        when(restaurantRepository.existsByName("restaurant")).thenReturn(true);

        boolean result = restaurantGateway.nameIsAvailable("restaurant");
        assertThat(result).isTrue();
    }

    @Test
    void shloudResturnFalseWhenNameIsNotAvailable() {
        when(restaurantRepository.existsByName("restaurant")).thenReturn(false);

        boolean result = restaurantGateway.nameIsAvailable("restaurant");
        assertThat(result).isFalse();
    }

    @Test
    void shouldSaveRestaurant() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);

        when(restaurantRepository.save(any())).thenReturn(any());

        restaurantGateway.save(restaurant);

        verify(restaurantRepository, times(1)).save(any());
    }

    @Test
    void shouldFindRestaurants() {
        fail();
    }

    @Test
    void shouldFindRestaurantById() {
        fail();
    }
}
