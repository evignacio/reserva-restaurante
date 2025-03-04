package br.com.fiap.reservarestaurante.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation extends Entity<String> {
    private String idRestaurant;
    private String idUser;
    private int numberOfClients;
    private LocalDateTime date;

    public Reservation(String idRestaurant, String idUser, int numberOfClients, LocalDateTime date) {
        super(UUID.randomUUID().toString());
        setIdRestaurant(idRestaurant);
        setIdUser(idUser);
        setNumberOfClients(numberOfClients);
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

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        if (numberOfClients <= 0 || numberOfClients > 10)
            throw new IllegalArgumentException("NumnerOfClients must be between 1 and 10");

        this.numberOfClients = numberOfClients;
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
