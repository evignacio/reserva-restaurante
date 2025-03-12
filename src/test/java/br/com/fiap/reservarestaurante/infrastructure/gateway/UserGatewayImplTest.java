package br.com.fiap.reservarestaurante.infrastructure.gateway;

import br.com.fiap.reservarestaurante.core.domain.User;
import br.com.fiap.reservarestaurante.infrastructure.repository.UserRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGatewayImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserGatewayImpl userGateway;

    @Test
    void shouldReturnTrueWhenEmailIsAvailable() {
        var email = "a@email.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        boolean result = userGateway.isEmailAvailable(email);
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseEmailIsNotAvailable() {
        var email = "a@email.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean result = userGateway.isEmailAvailable(email);
        assertThat(result).isFalse();
    }

    @Test
    void shouldSaveUser() {
        User user = new User("Evandro", "Pastor", 49, "evandro@email.com.br");

        when(userRepository.save(any())).thenReturn(any());

        userGateway.save(user);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void shouldFindUser() {

        String id = UUID.randomUUID().toString();

        var name = "user";
        var surName = "user user";
        var age = 20;
        var email = "email@email.com";

        UserModel userModel = UserModel.builder()
                .name(name)
                .surname(surName)
                .age(age)
                .email(email)
                .build();

        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(userModel));

        Optional<User> user = userGateway.findById(id);
        assertThat(user).isNotEmpty();
        user.ifPresent(user1 -> {
            assertThat(user1.getAge()).isEqualTo(age);
            assertThat(user1.getName()).isEqualTo(name);
            assertThat(user1.getSurname()).isEqualTo(surName);
            assertThat(user1.getEmail()).isEqualTo(email);
        });
    }
}
