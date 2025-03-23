package br.com.fiap.reservarestaurante.performance;


import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AppSilumation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

    ScenarioBuilder getUserScenario = scenario("Test get user")
            .exec(http("Get Users").get("/users/user@teste.com"));

    ScenarioBuilder getRestaurantScenario = scenario("Test get restaurant")
            .exec(http("Get Restaurant").get("/restaurants?name=\"Restaurant cool\""));

    {
        setUp(
                getUserScenario.injectOpen(
                        rampUsersPerSec(1)
                                .to(20)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(10)),
                        rampUsersPerSec(20)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ),
                getRestaurantScenario.injectOpen(
                        rampUsersPerSec(1)
                                .to(20)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(10)),
                        rampUsersPerSec(20)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ))
                .protocols(httpProtocol);


    }
}
