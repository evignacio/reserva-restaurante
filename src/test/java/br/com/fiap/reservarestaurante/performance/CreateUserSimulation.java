package br.com.fiap.reservarestaurante.performance;


import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class CreateUserSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

    ScenarioBuilder scn = scenario("Teste de carga users")
            .exec(http("Get Users").get("/users/user@teste.com"))
            .pause(5);

    {
        setUp(scn.injectOpen(atOnceUsers(10))).protocols(httpProtocol);
    }
}
