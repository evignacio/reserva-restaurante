package br.com.fiap.reservarestaurante.infrastructure.configuration;

import br.com.fiap.reservarestaurante.infrastructure.repository.CategoryRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.UserRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.CategoryModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.UserModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner runner(RestaurantRepository restaurantRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        return args -> {
            restaurantRepository.deleteAll();
            categoryRepository.deleteAll();
            userRepository.deleteAll();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            TypeReference<RestaurantModel> restaurantTypeReference = new TypeReference<>() {
            };
            InputStream restaurantInputStream = TypeReference.class.getResourceAsStream("/data/restaurant.json");
            RestaurantModel restaurantModel = mapper.readValue(restaurantInputStream, restaurantTypeReference);
            restaurantRepository.save(restaurantModel);
            System.out.println("Restaurant Data saved!");

            TypeReference<List<CategoryModel>> categoryModelTypeReference = new TypeReference<>() {
            };
            InputStream categoryInput = TypeReference.class.getResourceAsStream("/data/category.json");
            List<CategoryModel> categoryModel = mapper.readValue(categoryInput, categoryModelTypeReference);
            categoryRepository.saveAll(categoryModel);
            System.out.println("Category Data saved!");

            TypeReference<UserModel> userModelTypeReference = new TypeReference<>() {
            };
            InputStream userInput = TypeReference.class.getResourceAsStream("/data/user.json");
            UserModel userModel = mapper.readValue(userInput, userModelTypeReference);
            userRepository.save(userModel);
            System.out.println("User Data saved!");

        };
    }
}
