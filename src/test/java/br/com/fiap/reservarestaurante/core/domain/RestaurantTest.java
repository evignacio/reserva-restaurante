package br.com.fiap.reservarestaurante.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RestaurantTest {

    @Test
    void shouldCreateRestaurant() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);

        var result = assertDoesNotThrow(() -> new Restaurant("Restaurant Name", address, 50, category, workPeriods));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Restaurant Name");
        assertThat(result.getAddress()).isEqualTo(address);
        assertThat(result.getMaxCapacity()).isEqualTo(50);
        assertThat(result.getCategory()).isEqualTo(category);
        assertThat(result.getWorkPeriods()).isEqualTo(workPeriods);
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionNameNullOrEmpty(String name) {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);

        var exception = catchThrowable(() -> new Restaurant(name, address, 50, category, workPeriods));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionAddressNull() {
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", null, 50, category, workPeriods));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Address cannot be null");
    }

    @CsvSource({
            "0",
            "-1"
    })
    @ParameterizedTest
    void shouldReturnExceptionMaxCapacityInvalid(int maxCapacity) {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, maxCapacity, category, workPeriods));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Max capacity cannot be less than 1");
    }

    @Test
    void shouldReturnExceptionCategoryNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, 50, null, workPeriods));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category cannot be null");
    }

    @Test
    void shouldReturnExceptionWorkPeriodsNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");

        var exception = catchThrowable(() -> new Restaurant("Restaurant Name", address, 50, category, null));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Work periods cannot be null");
    }

    @Test
    void shouldReturnExceptionReservationsNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);

        var exception = catchThrowable(() -> {
            Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);
            restaurant.addReservation(null);
        });

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Reservation cannot be null");
    }

    @Test
    void shouldReturnExceptionReviewsNull() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);

        var exception = catchThrowable(() -> {
            Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);
            restaurant.addReview(null);
        });

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Review cannot be null");
    }

    @Test
    void shouldAddReservationSuccessfully() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, getFullWeekWorkPeriod(9, 22));
        Reservation reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().withHour(12));

        assertDoesNotThrow(() -> restaurant.addReservation(reservation));
        assertThat(restaurant.getReservations().contains(reservation)).isTrue();
    }

    @Test
    void shouldReturnExceptionReservationDateInPast() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);
        Reservation reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().minusDays(1));

        var exception = catchThrowable(() -> restaurant.addReservation(reservation));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Reservation date must be in the future");
    }

    @Test
    void shouldReturnExceptionReservationDateTooFarInFuture() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);
        Reservation reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().plusDays(3));

        var exception = catchThrowable(() -> restaurant.addReservation(reservation));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Reservation date must be made at most 2 days in advance");
    }

    @Test
    void shouldReturnExceptionRestaurantFullForRequestedDay() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 1, category, getFullWeekWorkPeriod(9, 22));
        Reservation reservation1 = new Reservation("restaurantId", "userId1", 1, LocalDateTime.now().withHour(12));
        Reservation reservation2 = new Reservation("restaurantId", "userId2", 1, LocalDateTime.now().withHour(13));
        restaurant.addReservation(reservation1);

        var exception = catchThrowable(() -> restaurant.addReservation(reservation2));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The restaurant is full for the requested day");
    }

    @Test
    void shouldReturnExceptionWorkPeriodNotFoundForReservationDate() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);
        Reservation reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().plusDays(1).withHour(23));

        var exception = catchThrowable(() -> restaurant.addReservation(reservation));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Work period not found for the reservation date");
    }

    @Test
    void shouldReturnFullCapacityWhenNoReservations() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, getFullWeekWorkPeriod(9, 22));

        int availableReservations = restaurant.getAmountOfReservationsAvailableForDay(LocalDateTime.now());
        assertThat(availableReservations).isEqualTo(50);
    }

    @Test
    void shouldReturnReducedCapacityWhenReservationsExist() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, getFullWeekWorkPeriod(9, 22));
        Reservation reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().withHour(12));
        restaurant.addReservation(reservation);

        int availableReservations = restaurant.getAmountOfReservationsAvailableForDay(LocalDateTime.now());
        assertThat(availableReservations).isEqualTo(45);
    }

    @Test
    void shouldReturnFullCapacityForDifferentDay() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, getFullWeekWorkPeriod(9, 22));
        Reservation reservation = new Reservation("restaurantId", "userId", 5, LocalDateTime.now().plusDays(1).withHour(12));
        restaurant.addReservation(reservation);

        int availableReservations = restaurant.getAmountOfReservationsAvailableForDay(LocalDateTime.now());
        assertThat(availableReservations).isEqualTo(50);
    }

    @Test
    void shouldReturnTrueWhenRestaurantIsOpen() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, getFullWeekWorkPeriod(9, 22));

        boolean isOpen = restaurant.isOpen(LocalDateTime.now().withHour(10));
        assertThat(isOpen).isTrue();
    }

    @Test
    void shouldReturnFalseWhenRestaurantIsClosed() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, getFullWeekWorkPeriod(9, 22));

        boolean isOpen = restaurant.isOpen(LocalDateTime.now().withHour(23));
        assertThat(isOpen).isFalse();
    }

    @Test
    void shouldReturnFalseWhenNoWorkPeriodForDay() {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        Set<WorkPeriod> workPeriods = new HashSet<>();
        workPeriods.add(new WorkPeriod(DayOfWeek.MONDAY, 9, 22));
        Restaurant restaurant = new Restaurant("Restaurant Name", address, 50, category, workPeriods);

        boolean isOpen = restaurant.isOpen(LocalDateTime.now().with(DayOfWeek.TUESDAY).withHour(10));
        assertThat(isOpen).isFalse();
    }

    private Set<WorkPeriod> getFullWeekWorkPeriod(int startHour, int endHour) {
        var workPeriods = new HashSet<WorkPeriod>();
        workPeriods.add(new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.TUESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.WEDNESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.THURSDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.FRIDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SATURDAY, startHour, endHour));
        workPeriods.add(new WorkPeriod(DayOfWeek.SUNDAY, startHour, endHour));
        return workPeriods;
    }
}