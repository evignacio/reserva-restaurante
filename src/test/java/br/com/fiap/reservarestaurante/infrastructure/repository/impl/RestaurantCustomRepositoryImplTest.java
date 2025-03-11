package br.com.fiap.reservarestaurante.infrastructure.repository.impl;

import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantCustomRepositoryImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private RestaurantCustomRepositoryImpl restaurantCustomRepository;

    @Test
    void shouldFindAllRestaurants() {
        AddressModel address = AddressModel.builder()
                .city("São Paulo")
                .country("Brazil")
                .state("SP")
                .street("Rua A")
                .number(123)
                .zipCode("12345678")
                .build();

        RestaurantModel restaurantModel = RestaurantModel.builder()
                .name("Restaurant Name")
                .address(address)
                .maxCapacity(20)
                .build();

        when(mongoTemplate.find(any(Query.class), any())).thenReturn(List.of(restaurantModel));

        Set<RestaurantModel> restaurants = restaurantCustomRepository.findAll("Restaurant Name", "IdCategory", address);
        assertThat(restaurants).isNotEmpty();
        assertThat(restaurants).hasSize(1);
    }

    @Test
    void shouldReturnEmptySetWhenNoRestaurantsFound() {
        AddressModel address = AddressModel.builder()
                .city("São Paulo")
                .country("Brazil")
                .state("SP")
                .street("Rua A")
                .number(123)
                .zipCode("12345678")
                .build();

        when(mongoTemplate.find(any(Query.class), any())).thenReturn(List.of());

        Set<RestaurantModel> restaurants = restaurantCustomRepository.findAll("Restaurant Name", "IdCategory", address);
        assertThat(restaurants).isEmpty();
    }

    @Test
    void shouldBuildQueryWithCorrectCriteria() {
        AddressModel address = AddressModel.builder()
                .city("São Paulo")
                .country("Brazil")
                .state("SP")
                .street("Rua A")
                .number(123)
                .zipCode("12345678")
                .build();

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("Restaurant Name"));
        query.addCriteria(Criteria.where("category.id").is("IdCategory"));
        query.addCriteria(Criteria.where("address.city").is(address.getCity())
                .and("address.state").is(address.getState())
                .and("address.country").is(address.getCountry()));

        when(mongoTemplate.find(query, RestaurantModel.class)).thenReturn(List.of());

        Set<RestaurantModel> restaurants = restaurantCustomRepository.findAll("Restaurant Name", "IdCategory", address);
        assertThat(restaurants).isEmpty();
    }
}