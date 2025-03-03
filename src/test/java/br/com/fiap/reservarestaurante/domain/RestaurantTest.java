package br.com.fiap.reservarestaurante.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RestaurantTest {

    @Test
    void shouldCreateRestaurant() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour);
        Set<Reservation> reservations = Set.of(new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));
        Set<Review> reviews = Set.of(new Review("idRestaurant", "idUser", 5, "Great place!"));

        var result = assertDoesNotThrow(() -> new Restaurant("Restaurant Name", address, 50, category, workPeriod, reservations, reviews));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Restaurant Name");
        assertThat(result.getAddress()).isEqualTo(address);
        assertThat(result.getMaxCapacity()).isEqualTo(50);
        assertThat(result.getCategory()).isEqualTo(category);
        assertThat(result.getWorkPeriod()).isEqualTo(workPeriod);
        assertThat(result.getReservations()).isEqualTo(reservations);
        assertThat(result.getReviews()).isEqualTo(reviews);
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionNameNullOrEmpty(String name) {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour);
        Set<Reservation> reservations = Set.of(new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));
        Set<Review> reviews = Set.of(new Review("idRestaurant", "idUser", 5, "Great place!"));

        var exception = catchThrowable(() -> new Restaurant(name, address, 50, category, workPeriod, reservations, reviews));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Name cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionAddressNull() {
        Category category = new Category("Italian");
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour);
        Set<Reservation> reservations = Set.of(new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));
        Set<Review> reviews = Set.of(new Review("idRestaurant", "idUser", 5, "Great place!"));

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", null, 50, category, workPeriod, reservations, reviews));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Address cannot be null");
    }

    @CsvSource({
            "0",
            "-1"
    })
    @ParameterizedTest
    void shouldReturnExceptionMaxCapacityInvalid(int maxCapacity) {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour);
        Set<Reservation> reservations = Set.of(new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));
        Set<Review> reviews = Set.of(new Review("idRestaurant", "idUser", 5, "Great place!"));

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, maxCapacity, category, workPeriod, reservations, reviews));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Max capacity cannot be less than 1");
    }

    @Test
    void shouldReturnExceptionCategoryNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour);
        Set<Reservation> reservations = Set.of(new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));
        Set<Review> reviews = Set.of(new Review("idRestaurant", "idUser", 5, "Great place!"));

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, 50, null, workPeriod, reservations, reviews));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Category cannot be null");
    }

    @Test
    void shouldReturnExceptionWorkPeriodNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Set<Reservation> reservations = Set.of(new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));
        Set<Review> reviews = Set.of(new Review("idRestaurant", "idUser", 5, "Great place!"));

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, 50, category, null, reservations, reviews));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Work period cannot be null");
    }

    @Test
    void shouldReturnExceptionReservationsNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour);
        Set<Review> reviews = Set.of(new Review("idRestaurant", "idUser", 5, "Great place!"));

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, 50, category, workPeriod, null, reviews));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Reservations cannot be null");
    }

    @Test
    void shouldReturnExceptionReviewsNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour);
        Set<Reservation> reservations = Set.of(new Reservation("restaurantId", "userId", 5, LocalDateTime.now()));

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, 50, category, workPeriod, reservations, null));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Reviews cannot be null");
    }
}
