package br.com.fiap.reservarestaurante.bdd;

import br.com.fiap.reservarestaurante.core.dto.CreateUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDefinitions {
    CreateUserDTO userDTO;
    String userEmail = "a@fiap.com.br";
    String nomeRestaurante = "Restaurant cool";
    String urlUsers = "/users";
    String urlRestaurantes = "/restaurants";
    Response userResponse;
    Response restaurantResponse;


    @Given("todos os dados de usuario foram preenchidos corretamente")
    public void todos_os_dados_de_usuario_foram_preenchidos_corretamente() {
        userDTO = new CreateUserDTO("abc", "bcd", 34, userEmail);
    }

    @When("a requisicao de criar usuario for executada")
    public void a_requisicao_de_criar_usuario_for_executada() throws JsonProcessingException {
        var objMapper = new ObjectMapper();
        var reqString = objMapper.writeValueAsString(userDTO);
        userResponse = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(reqString).when().post(urlUsers);
    }

    @Then("devo ter status {string}")
    public void devo_ter_status(String string) {
        assertEquals(HttpStatus.CREATED.value(), userResponse.getStatusCode());
    }

    @Given("email passado via url corretamente")
    public void email_passado_via_url_corretamente() {
        urlUsers = urlUsers.concat("/" + userEmail);
    }

    @When("disparar a requisicao get")
    public void disparar_a_requisicao_get() {
        userResponse = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).when().get(urlUsers);
    }

    @Then("devo encontrar dados de usuario e ter status {string}")
    public void devo_encontrar_dados_de_usuario_e_ter_status(String string) {
        assertNotNull(userResponse.body());
        assertEquals(HttpStatus.OK.value(), userResponse.getStatusCode());
    }

    @Given("nome do restaurante passado via url corretamente")
    public void nome_do_restaurante_passado_via_url_corretamente() {
        urlRestaurantes = urlRestaurantes.concat("?name=" + nomeRestaurante);
    }
    @When("disparar a requisicao get restaurante")
    public void disparar_a_requisicao_get_restaurante() {
        restaurantResponse = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).when().get(urlRestaurantes);
    }
    @Then("devo encontrar dados de restaurante e ter status {string}")
    public void devo_encontrar_dados_de_restaurante_e_ter_status(String string) {
        assertNotNull(restaurantResponse.body());
        assertEquals(HttpStatus.OK.value(), restaurantResponse.getStatusCode());
    }


}
