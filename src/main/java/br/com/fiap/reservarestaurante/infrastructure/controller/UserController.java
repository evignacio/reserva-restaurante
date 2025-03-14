package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.CreateUserDTO;
import br.com.fiap.reservarestaurante.core.dto.UserDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateUserUseCase;
import br.com.fiap.reservarestaurante.core.usecase.DeleteUserUseCase;
import br.com.fiap.reservarestaurante.core.usecase.FindUserUseCase;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("usersController")
@RequestMapping(value = "/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, DeleteUserUseCase deleteUserUseCase, FindUserUseCase findUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.findUserUseCase = findUserUseCase;
    }

    @GetMapping()
    ResponseEntity<UserDTO> findUserByEmail(@RequestParam("e") String email) {
        var response = findUserUseCase.execute(email);
        return ResponseEntity.ok(response);
    }


    @PostMapping()
    ResponseEntity<Void> createUser(@RequestBody CreateUserDTO user) {
        createUserUseCase.execute(user);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable String id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
