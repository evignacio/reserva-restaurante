package br.com.fiap.reservarestaurante.infrastructure.gateway;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Category;
import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.domain.WorkPeriod;
import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.CategoryModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.WorkPeriodModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
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
        AddressModel address = AddressModel.builder()
                .city("São Paulo")
                .country("Brazil")
                .state("SP")
                .street("Rua A")
                .number(123)
                .zipCode("12345678")
                .build();

        CategoryModel category = new CategoryModel("IdCategory","Italian");

        WorkPeriodModel workPeriod = WorkPeriodModel.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startHour(9)
                .endHour(22)
                .build();

        Set<WorkPeriodModel> workPeriods = Set.of(workPeriod);

        RestaurantModel restaurantModel = RestaurantModel.builder()
                .name("Restaurant Name")
                .address(address)
                .maxCapacity(20)
                .category(category)
                .workPeriods(workPeriods)
                .build();

        when(restaurantRepository.findAll()).thenReturn(List.of(restaurantModel));

        Set<Restaurant> restaurants = restaurantGateway.find("Restaurant Name", "IdCategory", new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678"));
        assertThat(restaurants).isNotEmpty();
        assertThat(restaurants).hasSize(1);
    }

    @Test
    void shouldFindRestaurantById() {
        AddressModel address = AddressModel.builder()
                .city("São Paulo")
                .country("Brazil")
                .state("SP")
                .street("Rua A")
                .number(123)
                .zipCode("12345678")
                .build();

        CategoryModel category = new CategoryModel("IdCategory","Italian");

        WorkPeriodModel workPeriod = WorkPeriodModel.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startHour(9)
                .endHour(22)
                .build();

        Set<WorkPeriodModel> workPeriods = Set.of(workPeriod);

        RestaurantModel restaurantModel = RestaurantModel.builder()
                .name("Restaurant Name")
                .address(address)
                .maxCapacity(20)
                .category(category)
                .workPeriods(workPeriods)
                .build();

        when(restaurantRepository.findById("IdRestaurant")).thenReturn(Optional.of(restaurantModel));

        Optional<Restaurant> restaurant = restaurantGateway.findById("IdRestaurant");
        assertThat(restaurant).isPresent();
        assertThat(restaurant.get().getName()).isEqualTo("Restaurant Name");
        assertThat(restaurant.get().getMaxCapacity()).isEqualTo(20);
        assertThat(restaurant.get().getCategory().getName()).isEqualTo(category.getName());
    }
}
