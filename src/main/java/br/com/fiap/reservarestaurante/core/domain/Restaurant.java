package br.com.fiap.reservarestaurante.core.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Restaurant extends Entity<String> {
    private final Set<WorkPeriod> workPeriods;
    private final Set<Reservation> reservations;
    private final Set<Review> reviews;
    private String name;
    private Address address;
    private int maxCapacity;
    private Category category;

    public Restaurant(String name, Address address, int maxCapacity, Category category, Set<WorkPeriod> workPeriods) {
        this();
        setName(name);
        setAddress(address);
        setMaxCapacity(maxCapacity);
        setCategory(category);
        setWorkPeriod(workPeriods);
    }

    public Restaurant() {
        super(UUID.randomUUID().toString());
        this.workPeriods = new HashSet<>();
        this.reservations = new HashSet<>();
        this.reviews = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null)
            throw new IllegalArgumentException("Address cannot be null");

        this.address = address;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity < 1)
            throw new IllegalArgumentException("Max capacity cannot be less than 1");

        this.maxCapacity = maxCapacity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("Category cannot be null");

        this.category = category;
    }

    public Set<WorkPeriod> getWorkPeriods() {
        return this.workPeriods;
    }

    public void setWorkPeriod(Set<WorkPeriod> workPeriods) {
        if (workPeriods == null)
            throw new IllegalArgumentException("Work periods cannot be null");

        this.workPeriods.addAll(workPeriods);
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        if (reservation == null)
            throw new IllegalArgumentException("Reservation cannot be null");

        var differenceOfDaysBetweenReservationAndNow = reservation.getDate().getDayOfMonth() - LocalDateTime.now().getDayOfMonth();
        if (differenceOfDaysBetweenReservationAndNow < 0)
            throw new IllegalArgumentException("Reservation date must be in the future");

        if (differenceOfDaysBetweenReservationAndNow > 2)
            throw new IllegalArgumentException("Reservation date must be made at most 2 days in advance");

        if (getAmountOfReservationsAvailableForDay(reservation.getDate()) <= 0)
            throw new IllegalArgumentException("The restaurant is full for the requested day");

        var workPeriodValidForReservation = getWorkPeriods()
                .stream()
                .filter(w ->
                        w.getDayOfWeek().equals(reservation.getDate().getDayOfWeek()) &&
                                reservation.getDate().getHour() > w.getStartHour() &&
                                reservation.getDate().getHour() < (w.getEndHour() - 1)
                )
                .findFirst();

        if (workPeriodValidForReservation.isEmpty())
            throw new IllegalArgumentException("Work period not found for the reservation date");

        this.reservations.add(reservation);
    }

    public int getAmountOfReservationsAvailableForDay(LocalDateTime date) {
        return getMaxCapacity() - getReservations()
                .stream()
                .filter(r -> r.getDate().getDayOfMonth() == date.getDayOfMonth())
                .map(Reservation::getAmountOfTables)
                .reduce(0, Integer::sum);
    }

    public boolean isOpen(LocalDateTime date) {
        return getWorkPeriods()
                .stream()
                .anyMatch(w ->
                        w.getDayOfWeek().equals(date.getDayOfWeek()) &&
                                date.getHour() > w.getStartHour() &&
                                date.getHour() < w.getEndHour()
                );
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        if (review == null)
            throw new IllegalArgumentException("Review cannot be null");

        this.reviews.add(review);
    }
}
