package br.com.fiap.reservarestaurante.infrastructure.configuration;

import br.com.fiap.reservarestaurante.infrastructure.repository.CategoryRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.CategoryModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner runner(RestaurantRepository restaurantRepository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            TypeReference<RestaurantModel> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/restaurant.json");
            RestaurantModel tabelaDePrecos = mapper.readValue(inputStream, typeReference);
            restaurantRepository.save(tabelaDePrecos);
            System.out.println("Data saved!");
        };
    }
}
