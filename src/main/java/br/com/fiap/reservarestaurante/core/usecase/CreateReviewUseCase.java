package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Review;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;

public class CreateReviewUseCase {

    private final RestaurantGateway restaurantGateway;
    private final UserGateway userGateway;

    public CreateReviewUseCase(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        this.restaurantGateway = restaurantGateway;
        this.userGateway = userGateway;
    }

    public void execute(Review review) {
        if (userGateway.findById(review.getIdUser()).isEmpty())
            throw new IllegalStateException("User not found");

        var restaurantOpt = restaurantGateway.findById(review.getIdRestaurant());

        if (restaurantOpt.isEmpty())
            throw new IllegalStateException("Restaurant not found");

        var restaurant = restaurantOpt.get();
        restaurant.addReview(review);

        restaurantGateway.save(restaurant);
    }
}
