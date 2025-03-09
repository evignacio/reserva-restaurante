package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    void shouldCreateUser() {
        var name = "Evandro";
        var surname = "Pastor";
        var age = 49;
        var email = "evandro@email.com.br";
        var evandroUser = new User(name, surname, age, email);

        when(userGateway.isEmailAvailable(email)).thenReturn(true);

        var result = assertDoesNotThrow(() -> createUserUseCase.execute(evandroUser));
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getSurname()).isEqualTo(surname);
        assertThat(result.getAge()).isEqualTo(age);
        assertThat(result.getEmail()).isEqualTo(email);

    }

    @Test
    void shouldReturnExceptionNameRestaurantNotAvailable() {
        var name = "Evandro";
        var surname = "Pastor";
        var age = 49;
        var email = "evandro@email.com.br";
        var evandroUser = new User(name, surname, age, email);
        when(userGateway.isEmailAvailable(email)).thenReturn(false);
        var exception = catchThrowable(() -> createUserUseCase.execute(evandroUser));
        assertThat(exception).isInstanceOf(IllegalStateException.class);
        assertThat(exception.getMessage()).isEqualTo("User email not available");
    }

}
