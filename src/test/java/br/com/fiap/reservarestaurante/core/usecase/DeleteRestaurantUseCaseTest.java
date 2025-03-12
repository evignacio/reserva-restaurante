package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteRestaurantUseCaseTest {

    @Mock
    private RestaurantGateway restaurantGateway;
    @Mock
    private Restaurant restaurant;

    @InjectMocks
    private DeleteRestaurantUseCase deleteRestaurantUseCase;

    @Test
    void shouldDeleteRestaurant() {
        var id = UUID.randomUUID().toString();

        when(restaurantGateway.findById(id)).thenReturn(Optional.of(restaurant));
        when(restaurant.getId()).thenReturn(id);

        assertDoesNotThrow(() -> deleteRestaurantUseCase.execute(id));
        verify(restaurantGateway, times(1)).delete(anyString());
    }


    @Test
    void shouldReturnExceptionRestaurantNotFound() {
        var id = UUID.randomUUID().toString();

        when(restaurantGateway.findById(id)).thenReturn(Optional.empty());

        var exception = catchThrowable(() -> deleteRestaurantUseCase.execute(id));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Restaurant not found");
        verify(restaurantGateway, times(0)).delete(id);
    }
}