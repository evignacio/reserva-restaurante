package br.com.fiap.reservarestaurante.core.usecase;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.core.gateway.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private DeleteUserUseCase deleteUserUseCase;

    @Test
    void shouldDeleteUser() {
        var id = UUID.randomUUID().toString();
        var name = "Evandro";
        var surname = "Pastor";
        var age = 49;
        var email = "evandro@email.com.br";
        var evandroUser = new User(name, surname, age, email);

        when(userGateway.findById(id)).thenReturn(Optional.of(evandroUser));

        assertDoesNotThrow(() -> deleteUserUseCase.execute(id));
        verify(userGateway, times(1)).delete(anyString());

    }

    @Test
    void shouldReturnExceptionNameRestaurantNotAvailable() {
        var id = UUID.randomUUID().toString();
        when(userGateway.findById(id)).thenReturn(Optional.empty());
        var exception = catchThrowable(() -> deleteUserUseCase.execute(id));
        assertThat(exception).isInstanceOf(IllegalStateException.class);
        assertThat(exception.getMessage()).isEqualTo("User not found.");
        verify(userGateway, times(0)).delete(anyString());
    }

}
