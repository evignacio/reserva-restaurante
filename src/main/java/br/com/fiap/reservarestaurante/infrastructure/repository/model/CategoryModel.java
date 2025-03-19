package br.com.fiap.reservarestaurante.infrastructure.repository.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@Document("category")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryModel extends Model<String> {
    @NotNull
    @NotBlank
    private String name;

    public CategoryModel(String id, String name) {
        super(id);
        this.name = name;
    }
}
