package br.com.fiap.reservarestaurante.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation extends Entity<String> {
    private String idRestaurant;
    private String idUser;
    private int amountOfTables;
    private LocalDateTime date;

    public Reservation(String idRestaurant, String idUser, int amountOfTables, LocalDateTime date) {
        super(UUID.randomUUID().toString());
        setIdRestaurant(idRestaurant);
        setIdUser(idUser);
        setAmountOfTables(amountOfTables);
        setDate(date);
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        if (idRestaurant == null || idRestaurant.isEmpty())
            throw new IllegalArgumentException("IdRestaurant cannot be null or empty");

        this.idRestaurant = idRestaurant;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        if (idUser == null)
            throw new IllegalArgumentException("IdUser cannot be null");

        this.idUser = idUser;
    }

    public int getAmountOfTables() {
        return amountOfTables;
    }

    public void setAmountOfTables(int amountOfTables) {
        if (amountOfTables <= 0 || amountOfTables > 5)
            throw new IllegalArgumentException("AmountOfTables must be between 1 and 5");

        this.amountOfTables = amountOfTables;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null)
            throw new IllegalArgumentException("Date cannot be null");

        this.date = date;
    }
}
