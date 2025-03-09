package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.*;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReviewUseCaseTest {

    @Mock
    private RestaurantGateway restaurantGateway;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private CreateReviewUseCase createReviewUseCase;

    @Test
    void shouldCreateReview() {
        var review = new Review("userId", "restaurantId", 5, "Great restaurant");
        var user = new User("userId", "User Name", 24);
        var address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        var workPeriod = new WorkPeriod(DayOfWeek.WEDNESDAY, 10, 22);
        var restaurant = new Restaurant("Restaurant Name", address, 50, new Category("Italian"), Set.of(workPeriod));

        when(userGateway.findById(review.getIdUser())).thenReturn(Optional.of(user));
        when(restaurantGateway.findById(review.getIdRestaurant())).thenReturn(Optional.of(restaurant));

        assertDoesNotThrow(() -> createReviewUseCase.execute(review));
    }

    @Test
    void shouldReturnExceptionUserNotFound() {
        var review = new Review("userId", "restaurantId", 5, "Great restaurant");

        when(userGateway.findById(review.getIdUser())).thenReturn(Optional.empty());

        var exception = catchException(() -> createReviewUseCase.execute(review));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("User not found");
    }

    @Test
    void shouldReturnExceptionRestaurantNotFound() {
        var user = new User("userId", "User Name", 24);
        var review = new Review("userId", "restaurantId", 5, "Great restaurant");

        when(userGateway.findById(review.getIdUser())).thenReturn(Optional.of(user));
        when(restaurantGateway.findById(review.getIdRestaurant())).thenReturn(Optional.empty());

        var exception = catchException(() -> createReviewUseCase.execute(review));

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Restaurant not found");
    }
}
