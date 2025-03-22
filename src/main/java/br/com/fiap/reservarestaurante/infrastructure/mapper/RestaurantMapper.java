package br.com.fiap.reservarestaurante.infrastructure.mapper;

import br.com.fiap.reservarestaurante.core.domain.*;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.*;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class RestaurantMapper {

    private RestaurantMapper() {
    }

    public static Restaurant toDomain(RestaurantModel restaurantModel) {
        var addressModel = restaurantModel.getAddress();
        var address = AddressMapper.toDomain(addressModel);

        Set<WorkPeriod> workPeriods = restaurantModel.getWorkPeriods()
                .stream()
                .map(w -> new WorkPeriod(w.getDayOfWeek(), w.getStartHour(), w.getEndHour()))
                .collect(Collectors.toSet());

        Set<Reservation> reservations = restaurantModel.getReservations()
                .stream()
                .map(r -> new Reservation(r.getId(), r.getIdRestaurant(), r.getIdUser(), r.getAmountOfTables(), r.getDate()))
                .collect(Collectors.toSet());

        Set<Review> reviews = restaurantModel.getReviews()
                .stream()
                .map(r -> new Review(r.getId(), r.getIdRestaurant(), r.getIdUser(), r.getRating(), r.getContent()))
                .collect(Collectors.toSet());

        return new Restaurant(
                restaurantModel.getId(),
                restaurantModel.getName(),
                address,
                restaurantModel.getMaxCapacity(),
                new Category(restaurantModel.getCategory().getId(), restaurantModel.getCategory().getName()),
                workPeriods,
                reservations,
                reviews
        );
    }

    public static RestaurantModel toModel(Restaurant restaurantDomain) {
        var addressModel = AddressMapper.toModel(restaurantDomain.getAddress());
        var categoryModel = CategoryModel.builder().name(restaurantDomain.getCategory().getName()).build();
        Set<WorkPeriodModel> workPeriodsModel = restaurantDomain.getWorkPeriods()
                .stream()
                .map(w -> WorkPeriodModel.builder()
                        .dayOfWeek(w.getDayOfWeek())
                        .startHour(w.getStartHour())
                        .endHour(w.getEndHour())
                        .build()
                ).collect(Collectors.toSet());

        Set<ReservationModel> reservationsModel = restaurantDomain.getReservations()
                .stream()
                .map(r -> ReservationModel.builder()
                        .id(r.getId())
                        .idRestaurant(r.getIdRestaurant())
                        .idUser(r.getIdUser())
                        .date(r.getDate())
                        .amountOfTables(r.getAmountOfTables())
                        .build()
                ).collect(Collectors.toSet());

        Set<ReviewModel> reviewsModel = restaurantDomain.getReviews()
                .stream()
                .map(r -> ReviewModel.builder()
                        .id(r.getId())
                        .idRestaurant(r.getIdRestaurant())
                        .idUser(r.getIdUser())
                        .content(r.getContent())
                        .rating(r.getRating())
                        .build()
                ).collect(Collectors.toSet());

        return RestaurantModel.builder()
                .id(restaurantDomain.getId())
                .name(restaurantDomain.getName())
                .address(addressModel)
                .maxCapacity(restaurantDomain.getMaxCapacity())
                .category(categoryModel)
                .workPeriods(workPeriodsModel)
                .reservations(reservationsModel)
                .reviews(reviewsModel)
                .build();
    }
}
