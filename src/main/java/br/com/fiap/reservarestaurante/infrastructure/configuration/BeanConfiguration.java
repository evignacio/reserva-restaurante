package br.com.fiap.reservarestaurante.infrastructure.configuration;

import br.com.fiap.reservarestaurante.core.gateway.CategoryGateway;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;
import br.com.fiap.reservarestaurante.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateReservationUseCase createReservationUseCase(RestaurantGateway restaurantGateway) {
        return new CreateReservationUseCase(restaurantGateway);
    }

    @Bean
    public CreateRestaurantUseCase createRestaurantUseCase(RestaurantGateway restaurantGateway, CategoryGateway categoryGateway) {
        return new CreateRestaurantUseCase(restaurantGateway, categoryGateway);

    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserGateway userGateway) {
        return new CreateUserUseCase(userGateway);
    }

    @Bean
    public DeleteRestaurantUseCase deleteRestaurantUseCase(RestaurantGateway restaurantGateway) {
        return new DeleteRestaurantUseCase(restaurantGateway);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserGateway userGateway) {
        return new DeleteUserUseCase(userGateway);
    }

    @Bean
    public FinalizeReservationUseCase finalizeReservationUseCase(RestaurantGateway restaurantGateway) {
        return new FinalizeReservationUseCase(restaurantGateway);
    }

    @Bean
    public FindUserUseCase findUserUseCase(UserGateway userGateway) {
        return new FindUserUseCase(userGateway);
    }

    @Bean
    public ListRestaurantsUseCase listRestaurantsUseCase(RestaurantGateway restaurantGateway) {
        return new ListRestaurantsUseCase(restaurantGateway);
    }

    @Bean
    public FindUserReservationsUseCase findUserReservationsUseCase(RestaurantGateway restaurantGateway) {
        return new FindUserReservationsUseCase(restaurantGateway);
    }

    @Bean
    public CreateReviewUseCase createReviewUseCase(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        return new CreateReviewUseCase(restaurantGateway, userGateway);
    }
}
