package br.com.fiap.reservarestaurante.infrastructure.repository.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserModel extends Model<String> {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private int age;
    @Email
    @NotBlank
    private String email;
}
