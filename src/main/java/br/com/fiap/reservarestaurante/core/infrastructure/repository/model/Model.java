package br.com.fiap.reservarestaurante.core.infrastructure.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Model<T> {

    @Id
    private T id;

    protected Model(T id) {
        this.id = id;
    }
}
