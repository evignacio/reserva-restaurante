package br.com.fiap.reservarestaurante.bdd;

import br.com.fiap.reservarestaurante.core.dto.CreateUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDefinitions {
    CreateUserDTO userDTO;
    int statusCode;

    @Given("todos os dados de usuario foram preenchidos corretamente")
    public void todos_os_dados_de_usuario_foram_preenchidos_corretamente() {
        userDTO = new CreateUserDTO("abc", "bcd", 34, "a@fiap.com.br");
    }

    @When("a requisicao de criar usuario for executada")
    public void a_requisicao_de_criar_usuario_for_executada() throws JsonProcessingException {
        var objMapper = new ObjectMapper();
        var reqString = objMapper.writeValueAsString(userDTO);
        var response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(reqString).when().post("/users");
        statusCode = response.getStatusCode();
    }

    @Then("devo ter status {string}")
    public void devo_ter_status(String string) {
        assertNotNull(statusCode);
        assertEquals(HttpStatus.CREATED.value(), statusCode);
    }

}
