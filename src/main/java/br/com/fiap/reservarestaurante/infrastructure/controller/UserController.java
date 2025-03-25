package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.CreateUserDTO;
import br.com.fiap.reservarestaurante.core.dto.UserDTO;
import br.com.fiap.reservarestaurante.core.usecase.CreateUserUseCase;
import br.com.fiap.reservarestaurante.core.usecase.DeleteUserUseCase;
import br.com.fiap.reservarestaurante.core.usecase.FindUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("usersController")
@RequestMapping(value = "/users")
class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, DeleteUserUseCase deleteUserUseCase, FindUserUseCase findUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.findUserUseCase = findUserUseCase;
    }

    @Operation(summary = "Buscar usuario por email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content)})
    @GetMapping("{email}")
    ResponseEntity<UserDTO> findUserByEmail(@PathVariable String email) {
        var response = findUserUseCase.execute(email);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Criar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content)})
    @PostMapping()
    ResponseEntity<Void> createUser(@RequestBody CreateUserDTO user) {
        createUserUseCase.execute(user);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @Operation(summary = "Deletar usurio por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content)})
    @DeleteMapping(value = "{id}")
    ResponseEntity<Void> deleteUser(@PathVariable String id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
