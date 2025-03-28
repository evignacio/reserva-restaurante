package br.com.fiap.reservarestaurante.core.domain;


import java.util.UUID;

public class Review extends Entity<String> {
    private String idUser;
    private String idRestaurant;
    private int rating;
    private String content;

    public Review(String idRestaurant, String idUser, int rating, String content) {
        super(UUID.randomUUID().toString());
        setIdRestaurant(idRestaurant);
        setIdUser(idUser);
        setRating(rating);
        setContent(content);
    }

    public Review(String id, String idUser, String idRestaurant, int rating, String content) {
        super(id);
        setIdRestaurant(idRestaurant);
        setIdUser(idUser);
        setRating(rating);
        setContent(content);
    }

    public String getIdUser() {
        return idUser;
    }

    private void setIdUser(String idUser) {
        if (idUser == null)
            throw new IllegalArgumentException("IdUser cannot be null");

        this.idUser = idUser;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    private void setIdRestaurant(String idRestaurant) {
        if (idRestaurant == null)
            throw new IllegalArgumentException("IdRestaurant cannot be null");

        this.idRestaurant = idRestaurant;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");

        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty())
            throw new IllegalArgumentException("Content cannot be null or empty");

        if (content.length() < 5 || content.length() > 60)
            throw new IllegalArgumentException("Content length must be between 5 and 255");

        this.content = content;
    }
}
