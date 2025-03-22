package br.com.fiap.reservarestaurante.core.dto;

public record ReviewDTO(String idRestaurant, String idUser, int rating, String content) {
}
