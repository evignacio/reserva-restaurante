package br.com.fiap.reservarestaurante.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation extends Entity<String> {
    private String idRestaurant;
    private String idUser;
    private int numnerOfClients;
    private LocalDateTime date;

    public Reservation(String idRestaurant, String idUser, int numnerOfClients, LocalDateTime date) {
        super(UUID.randomUUID().toString());
        setIdRestaurant(idRestaurant);
        setIdUser(idUser);
        setNumnerOfClients(numnerOfClients);
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

    public int getNumnerOfClients() {
        return numnerOfClients;
    }

    public void setNumnerOfClients(int numnerOfClients) {
        if (numnerOfClients <= 0 || numnerOfClients > 10)
            throw new IllegalArgumentException("NumnerOfClients must be between 1 and 10");

        this.numnerOfClients = numnerOfClients;
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
