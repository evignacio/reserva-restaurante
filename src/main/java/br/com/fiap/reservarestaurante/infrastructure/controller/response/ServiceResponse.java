package br.com.fiap.reservarestaurante.infrastructure.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceResponse<T> {
    private T data;
    private Message message;

    private ServiceResponse(T data, Message message) {
        this.data = data;
        this.message = message;
    }

    public static <T> ServiceResponse<T> build(T data) {
        return new ServiceResponse<>(data, buildSucessMessage());
    }

    public static <T> ServiceResponse<T> build() {
        return new ServiceResponse<>(null, buildSucessMessage());
    }

    private static Message buildSucessMessage() {
        return new Message(1, "Operacao realizada com sucesso");
    }

    @Data
    @AllArgsConstructor
    public static class Message {
        private int code;
        private String description;
    }
}
