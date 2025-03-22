package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.Review;
import br.com.fiap.reservarestaurante.core.dto.ReviewDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;

public class CreateReviewUseCase {

    private final RestaurantGateway restaurantGateway;
    private final UserGateway userGateway;

    public CreateReviewUseCase(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        this.restaurantGateway = restaurantGateway;
        this.userGateway = userGateway;
    }

    public void execute(ReviewDTO reviewDTO) {
        if (userGateway.findById(reviewDTO.idUser()).isEmpty())
            throw new IllegalStateException("User not found");

        var restaurant = restaurantGateway.findById(reviewDTO.idRestaurant())
                .orElseThrow(() -> new IllegalStateException("Restaurant not found"));

        var review = new Review(
                reviewDTO.idRestaurant(),
                reviewDTO.idUser(),
                reviewDTO.rating(),
                reviewDTO.content()
        );
        restaurant.addReview(review);
        restaurantGateway.save(restaurant);
    }
}
