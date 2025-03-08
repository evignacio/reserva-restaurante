package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Category;
import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.domain.WorkPeriod;
import br.com.fiap.reservarestaurante.core.gateway.CategoryGateway;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateRestaurantUseCaseTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private CreateRestaurantUseCase createRestaurantUseCase;

    @Test
    void shouldCreateRestaurant() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        var restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);

        when(restaurantGateway.nameIsAvailable("Restaurant Name")).thenReturn(true);
        when(categoryGateway.findById(category.getId())).thenReturn(Optional.of(category));

        var result = assertDoesNotThrow(() -> createRestaurantUseCase.execute(restaurant));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Restaurant Name");
        assertThat(result.getAddress()).isEqualTo(address);
        assertThat(result.getMaxCapacity()).isEqualTo(50);
        assertThat(result.getCategory()).isEqualTo(category);
        assertThat(result.getWorkPeriods()).isEqualTo(workPeriods);
    }

    @Test
    void shouldReturnExceptionNameRestaurantNotAvailable() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        var restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);

        when(restaurantGateway.nameIsAvailable("Restaurant Name")).thenReturn(false);

        var exception = catchThrowable(() -> createRestaurantUseCase.execute(restaurant));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Name restaurant not available");
    }

    @Test
    void shouldReturnExceptionCategoryNotFound() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        var restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);

        when(restaurantGateway.nameIsAvailable("Restaurant Name")).thenReturn(true);
        when(categoryGateway.findById(category.getId())).thenReturn(Optional.empty());

        var exception = catchThrowable(() -> createRestaurantUseCase.execute(restaurant));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Category not found");
    }
}
