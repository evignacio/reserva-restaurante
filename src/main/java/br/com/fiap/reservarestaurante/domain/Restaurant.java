package br.com.fiap.reservarestaurante.domain;

import java.util.Set;
import java.util.UUID;

public class Restaurant extends Entity<String> {
    private String name;
    private Address address;
    private int maxCapacity;
    private Category category;
    private WorkPeriod workPeriod;
    private Set<Reservation> reservations;
    private Set<Review> reviews;

    public Restaurant(String name, Address address, int maxCapacity, Category category, WorkPeriod workPeriod, Set<Reservation> reservations, Set<Review> reviews) {
        super(UUID.randomUUID().toString());
        setName(name);
        setAddress(address);
        setMaxCapacity(maxCapacity);
        setCategory(category);
        setWorkPeriod(workPeriod);
        setReservations(reservations);
        setReviews(reviews);
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

    public WorkPeriod getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(WorkPeriod workPeriod) {
        if (workPeriod == null)
            throw new IllegalArgumentException("Work period cannot be null");

        this.workPeriod = workPeriod;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (reservations == null)
            throw new IllegalArgumentException("Reservations cannot be null");

        this.reservations = reservations;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        if (reviews == null)
            throw new IllegalArgumentException("Reviews cannot be null");

        this.reviews = reviews;
    }
}
